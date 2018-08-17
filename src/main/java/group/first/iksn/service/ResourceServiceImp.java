package group.first.iksn.service;

import group.first.iksn.model.bean.*;
import group.first.iksn.model.bean.ReportResource;
import group.first.iksn.model.dao.ResourceDAO;
import group.first.iksn.model.dao.UserDAO;
import group.first.iksn.util.Inspect;
import group.first.iksn.util.LocalTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;

@Component("resourceService")
public class ResourceServiceImp  implements ResourceService{
    private ResourceDAO resourceDAO;
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ResourceDAO getResourceDAO() {
        return resourceDAO;
    }

    public void setResourceDAO(ResourceDAO resourceDAO) {
        this.resourceDAO = resourceDAO;
    }

    //评论评分
    public boolean assess(ResourceComments c) {
        System.out.println(c);
        return resourceDAO.assessResource(c);
    }

    //收藏资源
    public boolean houseResource(CollectResource h) {
        System.out.println(h);
        return resourceDAO.collectResource(h);
    }

    @Override
    public ArrayList<Resource> searchResource(String s) {
        System.out.println("servince层"+s);
        return resourceDAO.searchResource(s);
    }

    @Override
    public int downResource(Integer rid) {
        return resourceDAO.downnum(rid);
    }

    @Override
    public Resource loadResource(int rid) {
        return resourceDAO.getResource(rid);
    }

    @Override
    //上传文件，在service检测重复
    public boolean checkResource(CommonsMultipartFile file, String filePath) {

        File newFile = new File(filePath);//资源保存路径


        // 判断父级目录是否存在，不存在则创建
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdir();
        }
        // 判断文件是否存在，否则创建文件（夹）
        if (!newFile.exists()) {
            newFile.mkdir();
        }
        //通过CommonsMultipartFile的方法直接写文件
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String SHA = Inspect.getSHA(newFile);
        String MD5 = Inspect.getMD5(newFile);

        Resource r=resourceDAO.checkFile(MD5,SHA);
        System.out.println("NBXX"+r);
        if(r!=null){
            newFile.delete();
            return false;//说明存在相同资源
        }

        return true;
    }

    @Override
    public boolean upLoadResourc(Resource resource, String[] rTag) {
        //D:\AppSCM\IKSN\out\artifacts/resourcefile/2/99cc36d3d539b600385af78be850352ac65cb77c.jpg
        String filePath=resource.getPath();
        File f=new File(filePath);

        String SHA = Inspect.getSHA(f);
        String MD5 = Inspect.getMD5(f);
        resource.setMd5(MD5);
        resource.setSha(SHA);

        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resource.setTime(df.format(d));

        int index=filePath.lastIndexOf("resourcefile/");
        filePath=filePath.substring(index);
        resource.setPath(filePath);

        try {
            int addID = resourceDAO.addResource(resource);
            System.out.println("XSSS" + resource);
            /**
             *
             * 添加TAG表
             */
            List<String> list = Arrays.asList(rTag);
            boolean isTag = resourceDAO.addResourceTag(resource.getRid(), list);
            System.out.println(isTag + "PP" + list);
        }catch (Exception e){

        }
        return false;
    }

    /**
     * 删除ReportResource一行
     * wenbin
     * @param report_id
     * @return
     */
    @Override
    public boolean Reject_oneReportResource(int report_id) {
        return resourceDAO.deleteResourceFromReport(report_id);
    }

    @Override
    public boolean deleteIllegalResource(int resourceid) {
        boolean result=false;
        //获取uid
        Resource r=resourceDAO.selectUidByRid(resourceid);
        //先删除跟resource有关的表数据
        boolean deleteResult=resourceDAO.deleteResourceOthers(resourceid);
        if(deleteResult){
            //再删除resource表数据
            result=resourceDAO.deleteResource(resourceid);
            //封装notice(通知)
            Notice notice=new Notice();
            notice.setUid(r.getUid());
            notice.setContent("您有一个违规资源，已被删除！！");
            String time=LocalTime.getNowTime();
            notice.setTime(time);
            userDAO.addNotice(notice);
        }
        System.out.println("删除resource其他"+deleteResult);
        return result;
    }
    //举报资源
    @Override
    public boolean reportResource(ReportResource reportResource) {
        System.out.println(reportResource);
        boolean result=resourceDAO.reportResource(reportResource);
        return result;
    }


    @Override
    public List<ReportResource> getAllReportResource(int page) {
        return resourceDAO.getAllReportResource(page);
    }

    @Override
    public int reportResourceNum() {
        return resourceDAO.reportResourceNum();
    }

    @Override
    public boolean downLoadResource(int pushId, int downId, int scoring) {
        try {
            int pushScore=userDAO.getId(pushId).getScore();
            int downScore=userDAO.getId(downId).getScore();
            boolean isLessen=resourceDAO.changeScore(downId, downScore-scoring);//下载者减少后的积分
            boolean isnAdd=resourceDAO.changeScore(pushId, pushScore+scoring);//上传者增加后的积分
            if(!isLessen || !isnAdd) return false;
        }catch (Exception e) {
            return false;
        }
        return true;
    }


}
