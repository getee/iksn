package group.first.iksn.model.dao;

import group.first.iksn.model.bean.Blog;
import group.first.iksn.model.bean.IllegalBlog;
import group.first.iksn.model.bean.BlogTag;
import group.first.iksn.model.bean.UserToBlog;
import group.first.iksn.model.bean.ReportBlog;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("blogDAO")
public class BlogDAOImp extends BaseDAOImp implements BlogDAO {
    public String detailedBlogSearchResultMap() {
        return null;
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
    public List<ReportBlog> getAllReportBlog() {
        List<ReportBlog> allReportBlog=null;
        try {
            allReportBlog=getSqlSession().getMapper(BlogDAO.class).getAllReportBlog();
            System.out.println("22222");
        }catch (Exception e){
            System.out.println("这是获取违规博客出错了");
            e.printStackTrace();
        }
        return allReportBlog;
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
    public List<Blog> processScanBlog(int bid) {
        List<Blog> blog= getSqlSession().getMapper(BlogDAO.class).processScanBlog(bid);
        return blog;
    }



    /**
     * 从reportBlog表中删除一个选中行
     * wenbin
     * @param blog
     * @return
     */
    @Override
    public boolean deleteBlogFromReport(ReportBlog blog) {
        boolean isOK=false;
        try{
            isOK=getSqlSession().getMapper(BlogDAO.class).deleteBlogFromReport(blog);
        }catch (Exception e){
            System.out.println("这是添加违规博客出错了");
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
}
