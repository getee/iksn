package group.first.iksn.service;

import group.first.iksn.model.bean.*;

import java.util.List;

import java.util.Map;

public interface BlogService {
    //删除违规博客
    boolean deleteIllegalblog(int Blog_id,int  report_id);
    //下架违规博客
    boolean sendBackIllegalblog(IllegalBlog blog,int report_id);

    //添加mGetAllReportBlog的服务层
    List<ReportBlog> getAllReportBlog();
    //添加mGetAllReportResource的服务层
    List<ReportResource> getAllReportResource();
    //回违规博客，处理违规博客的安置
    boolean Reject_oneReportblog(int report_id);
    //添加Blog的服务层
    public boolean addBlogService(Blog blog);
    //添加BlogTag的服务层
    public boolean addBlogTagService(BlogTag blogTag);
    //添加UserToBlog的服务层
    public  boolean addUserToBlogService(UserToBlog userToBlog);

    public List<Blog> scanBlogService(int bid);

    boolean discuss(BlogComments blogComments);

    boolean answerComment(BlogComments blogComments);

    //举报博客
    public boolean reportBlog(ReportBlog reportBlog);
}
