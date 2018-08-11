package group.first.iksn.service;


import group.first.iksn.model.bean.Blog;
import group.first.iksn.model.bean.IllegalBlog;
import group.first.iksn.model.bean.BlogTag;
import group.first.iksn.model.bean.UserToBlog;
import group.first.iksn.model.dao.BlogDAO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
     * 删除违规博客，
     * wenbin
     * @return
     */
    @Override
    public boolean deleteIllegalblog(IllegalBlog blog) {
        return true;
    }

    /**
     * 退回违规博客，处理违规博客的安置
     * wenbin
     * @return
     */
    @Override
    public boolean sendBackIllegalblog(IllegalBlog blog) {

        return blogDAO.addIllegalblog(blog);
    }


    @Override
    public List<IllegalBlog> getAllReportBlog() {
        return blogDAO.getAllReportBlog();
    }



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
    public List<Blog> scanBlogService(int bid) {

        return blogDAO.processScanBlog(bid);
    }


}
