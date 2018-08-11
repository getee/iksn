package group.first.iksn.control;


import group.first.iksn.model.bean.Blog;
import group.first.iksn.model.bean.BlogTag;
import group.first.iksn.model.bean.UserToBlog;
import group.first.iksn.service.BlogService;
import group.first.iksn.util.EncodingTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.ibatis.jdbc.Null;

import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/blog")
public class BlogControl {
    private BlogService blogService;


    public BlogService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = "/blogSearch")
    public String bSearch(@RequestParam("content") String textcontent ){
        textcontent=EncodingTool.encodeStr(textcontent);//先将中文乱码转成UTF-8

        System.out.println(textcontent);
        return "sousuo";
    }
    /**
     *
     * @param blog_id
     * @return
     */
    @RequestMapping("/managerDeleteBlogForReported")
    @ResponseBody//此注解不能省略 否则ajax无法接受返回值
    public String managerDeleteBlogForReported(int blog_id){
        System.out.println("调用managerDeleteBlogForReported");
        return "success";
    }


@RequestMapping(value = "/addBlog",method = RequestMethod.POST)
    public  String  addBlog(@ModelAttribute ("blog")  Blog blog,@ModelAttribute ("blogTag") BlogTag blogTag,@ModelAttribute ("userToBlog") UserToBlog userToBlog) {
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    blog.setTime(df.format(d));
    blog.setTitle(EncodingTool.encodeStr(blog.getTitle()));//解决汉字乱码问题
    blog.setContent(EncodingTool.encodeStr(blog.getContent()));
    System.out.println(blog);
    boolean result = blogService.addBlogService(blog);
    //因为的多张表关联，要首先把主表的数据插入完成在进行其他副表的数据插入
    if (result == true) {
        //对blogTag表进行数据插入
        blogTag.setBid(7);
        blogTag.setBtag(EncodingTool.encodeStr(blogTag.getBtag()));
        System.out.println(blogTag);
        boolean result1 = blogService.addBlogTagService(blogTag);
        //对blogTag表进行数据插入
        userToBlog.setUid(6);
        userToBlog.setBid(7);
        userToBlog.setIsdraft(0);
        System.out.println(userToBlog);
        boolean result2=blogService.addUserToBlogService(userToBlog);
        if (result1 == true&& result2==true) {
            return "userArticle";
        } else {
            return "Writer";
        }
    }
    else
        return "Writer";
    }
    //根据bid来查询博客的相应数据
    @RequestMapping("/listBlogByBid")
    public  String selectBlogByID(){

        List<Blog> bl=blogService.scanBlogService(4);

            System.out.println(bl);
        return "index";
    }

}
