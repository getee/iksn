package group.first.iksn.model.dao;

import group.first.iksn.model.bean.*;
import org.springframework.stereotype.Component;

import java.rmi.server.UID;
import java.util.List;
import java.util.Map;

@Component("blogDAO")
public class BlogDAOImp extends BaseDAOImp implements BlogDAO {

    public List<Blog> detailedBlogSearchResultMap(String s) {
        List<Blog> b=getSqlSession().getMapper(BlogDAO.class).detailedBlogSearchResultMap(s);
        return b;
    }


    /**
     * 首页推送
     * @return
     */
    @Override
    public List<Blog> detailedBlogPush() {
        List<Blog> b=getSqlSession().getMapper(BlogDAO.class).detailedBlogPush();
        return b;
    }


    @Override
    public boolean processAddBlog(Blog blog) {
        boolean result= getSqlSession().getMapper(BlogDAO.class).processAddBlog(blog);
        return result;
    }

    /**
     * 向Illegalblog表添加一个对象
     * wenbin
     * @param blog
     * @return
     */
    @Override
    public boolean addIllegalblog(IllegalBlog blog) {
        boolean isOK=false;
        try{
            isOK=getSqlSession().getMapper(BlogDAO.class).addIllegalblog(blog);
            isOK=true;
        }catch (Exception e){
            System.out.println("这是添加违规博客出错了");
            e.printStackTrace();
        }
        return isOK;
    }

    /**
     * 获取违规表的所有行（此处应该获取举报表的，后续更改）
     * wenbin
     * @return
     */
    @Override
    public List<ReportBlog> getAllReportBlog(int page) {
        List<ReportBlog> allReportBlog=null;
        try {
            allReportBlog=getSqlSession().getMapper(BlogDAO.class).getAllReportBlog((page-1)*3);
            System.out.println("22222");
        }catch (Exception e){
            System.out.println("这是获取违规博客出错了");
            e.printStackTrace();
        }
        return allReportBlog;
    }

    /**
     * 获取所有被举报资源
     * wenbin
     * @return
     */
    @Override
    public List<ReportResource> getAllReportResource() {
        List<ReportResource> allReportResource=null;
        try {
            allReportResource=getSqlSession().getMapper(BlogDAO.class).getAllReportResource();
            System.out.println("获取举报资源成功");
        }catch (Exception e){
            System.out.println("这是获取违规资源出错了");
            e.printStackTrace();
        }
        return allReportResource;
    }

    @Override
    public boolean processAddBlogTag(BlogTag blogTag) {
        boolean result= getSqlSession().getMapper(BlogDAO.class).processAddBlogTag(blogTag);
        return result;
    }

    @Override
    public boolean processAddUserToBlog(UserToBlog userToBlog) {
        boolean result= getSqlSession().getMapper(BlogDAO.class).processAddUserToBlog(userToBlog);
        return result;
    }

    @Override
    public List<Blog> processScanBlog(int uid) {
        List<Blog> blog= getSqlSession().getMapper(BlogDAO.class).processScanBlog(uid);
        return blog;
    }

    @Override
    public Blog processListBlog(int bid) {
        Blog blog= getSqlSession().getMapper(BlogDAO.class).processListBlog(bid);
        return blog;
    }



    @Override
    public int selectBid(String time) {
        return getSqlSession().getMapper(BlogDAO.class).selectBid(time);
    }


    /**
     * 从reportBlog表中删除一个选中行
     * wenbin
     * @param report_id
     * @return
     */
    @Override
    public boolean deleteBlogFromReport(int report_id) {
        boolean isOK=false;
        try{
            isOK=getSqlSession().getMapper(BlogDAO.class).deleteBlogFromReport(report_id);
        }catch (Exception e){
            System.out.println("这是添加违规博客出错了");
            e.printStackTrace();
        }
        return isOK;
    }
//博客评论
    @Override
    public boolean commentBlog(BlogComments blogComments) {
        System.out.println(blogComments);
        return  getSqlSession().getMapper(BlogDAO.class).commentBlog(blogComments);
    }
//博客评论回复
    @Override
    public boolean answerDiscuss(BlogComments blogComments) {
        System.out.println(blogComments);
        return getSqlSession().getMapper(BlogDAO.class).answerDiscuss(blogComments);
    }

    @Override
    public String selectFloor(Integer bid) {
        return getSqlSession().getMapper(BlogDAO.class).selectFloor(bid);
    }

    @Override
    public int reportBlogNum() {
        return getSqlSession().getMapper(BlogDAO.class).reportBlogNum();
    }

    @Override
    public UserToBlog getUserIsSpeak(int bid) {
        return getSqlSession().getMapper(BlogDAO.class).getUserIsSpeak(bid);
    }

    @Override
    public boolean shutUptoUser(User user){
        return getSqlSession().getMapper(BlogDAO.class).shutUptoUser(user);
    }

    /**
     * 删除博客
     * wenbin
     * @param bid
     * @return
     */
    @Override
    public boolean deleteBlog(int bid) {
        boolean isOK=false;
        System.out.println(bid);
        try{
            isOK=getSqlSession().getMapper(BlogDAO.class).deleteBlog(bid);
        }catch (Exception e){
            System.out.println("这是删除博客出错了");
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public boolean deleteBlogOthers(int bid) {
        boolean isOK=false;
        try{
            isOK=getSqlSession().getMapper(BlogDAO.class).deleteBlogOthers(bid);
        }catch (Exception e){
            System.out.println("这是删除博客其它出错了");
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public boolean blogIsPublic(int bid) {
        boolean isOK=false;
        try{
            isOK=getSqlSession().getMapper(BlogDAO.class).blogIsPublic(bid);
        }catch (Exception e){
            System.out.println("这是更改博客不公开出错了");
            e.printStackTrace();
        }
        return isOK;
    }

    /**
     * 把前台举报的博客保存到数据库中
     * @param reportBlog
     * @return
     */
    @Override
    public boolean reportBlog(ReportBlog reportBlog) {
        boolean result=getSqlSession().getMapper(BlogDAO.class).reportBlog(reportBlog);
        return result;
    }

    @Override
    public ReportBlog selectReportBlog(int id) {
        ReportBlog reportBlog=getSqlSession().getMapper(BlogDAO.class).selectReportBlog(id);
        return reportBlog;
    }



    //我收藏的博客
    @Override
    public List<Blog> myCollectBlog(int uid) {
        List<Blog> collectblog=getSqlSession().getMapper(BlogDAO.class).myCollectBlog(uid);
        return collectblog;

    }

    //我发布的博客
    @Override
    public List<Blog> myBlog(int uid) {
        return getSqlSession().getMapper(BlogDAO.class).myBlog(uid);
    }
}
