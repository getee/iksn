package group.first.iksn.service;


import group.first.iksn.model.bean.*;
import group.first.iksn.model.dao.BlogDAO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;


@Component("blogService")
public class BlogServiceImp implements BlogService {
    private BlogDAO blogDAO;

    public BlogDAO getBlogDAO() {
        return blogDAO;
    }

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    /**
     * 删除违规博客
     * wenbin
     * @param blog_id
     * @param report_id
     * @return
     */
    @Override
    public boolean deleteIllegalblog(int blog_id,int  report_id) {
        boolean result=false;
        //boolean deleteResult=blogDAO.deleteBlog(blog_id);
        boolean deleteResult=blogDAO.deleteBlogOthers(blog_id);
        if(deleteResult){
            result=blogDAO.deleteBlog(blog_id);
        }
        System.out.println("删除blog其他"+deleteResult);
        return result;
    }

    /**
     * 退回违规博客，处理违规博客的安置
     * wenbin
     * @return
     */
    @Override
    public boolean sendBackIllegalblog(IllegalBlog blog,int report_id) {
        boolean sendBack=blogDAO.addIllegalblog(blog);
        if (sendBack){
            //插入illegalblog成功，将reportblog表对应数据删除
            blogDAO.deleteBlogFromReport(report_id);
            //设置博客为不可见
            boolean b=blogDAO.blogIsPublic(blog.getBid());
        }
        return sendBack;
    }

    /**
     * 获取被举报的博客
     * wenbin
     * @return
     */
    @Override
    public List<ReportBlog> getAllReportBlog() {
        return blogDAO.getAllReportBlog();
    }

    @Override
    public List<ReportResource> getAllReportResource() {
        return blogDAO.getAllReportResource();
    }

    /**
     * 驳回被举报的博客，（将博客去除被举报标记）
     * @param report_id
     * @return
     */
    @Override
    public boolean Reject_oneReportblog(int report_id) {
        return blogDAO.deleteBlogFromReport(report_id);
    }

    /**
     * 搜索资源的方法
     * @param
     * @return
     */
    public List<Blog> detailedBlogSearchResultMap(String s){

        return  blogDAO.detailedBlogSearchResultMap(s);
    }

    @Override
    public List<Blog> blogClassify(String s) {
        return  blogDAO.blogClassify(s);
    }

    @Override
    public List<Blog> blogTitle(String s) {
        /*String[] keyword=s.split("\\s+");*/

        return  blogDAO.blogTitle(s);
    }

    @Override
    public  List<String> ajaxBlogMohuSearch() {
        return blogDAO.ajaxBlogMohuSearch() ;
    }

    /**
     * 首页推送的方法
     * @return
     */
    @Override
    public List<Blog> detailedBlogPush(int page) {
        return blogDAO.detailedBlogPush(page);
    }
    public List<Blog> pointsPush(){ return blogDAO.pointsPush();}

    @Override
    public List<Blog> browsedPush(int classify) {
        return blogDAO.browsedPush(classify);
    }

    /**
     *
     * @param
     * @return
     */
   public List<Blog> ajaxBlogPush(int page){return blogDAO.ajaxBlogPush(page);}

    @Override
    public boolean addBlogService(Blog blog) {
        return blogDAO.processAddBlog(blog);
    }

    @Override
    public boolean addBlogTagService(BlogTag blogTag) {
        return blogDAO.processAddBlogTag(blogTag);
    }

    @Override
    public boolean addUserToBlogService(UserToBlog userToBlog) {
        return blogDAO.processAddUserToBlog(userToBlog);
    }

    @Override
    public List<Blog> scanBlogService(int uid) {
        return blogDAO.processScanBlog(uid);
    }

    @Override
    public Blog listBlogService(int bid) {
        return blogDAO.processListBlog(bid);
    }

    @Override
    public int selectBidService(String time) {
        return blogDAO.selectBid(time);
    }

    @Override
    public boolean discuss(BlogComments blogComments) {
        System.out.println(blogComments);
        return blogDAO.commentBlog(blogComments);
    }

    @Override
    public boolean answerComment(BlogComments blogComments) {
        System.out.println(blogComments);
        return blogDAO.answerDiscuss(blogComments);
    }

    //举报博客
    @Override
    public boolean reportBlog(ReportBlog reportBlog) {
        boolean serviceResult=blogDAO.reportBlog(reportBlog);
        return serviceResult;
    }

    @Override
    public String getFloor(Integer bid) {
        return blogDAO.selectFloor(bid);
    }


}
