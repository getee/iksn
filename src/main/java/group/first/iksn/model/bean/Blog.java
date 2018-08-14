package group.first.iksn.model.bean;

import java.util.Set;

public class Blog {
    private int bid;//博客id
    private String title;//博客标题
    private String content;//博客内容,根据富文本制定
    private  String time;//博客发布时间
    private  String link;//博客链接
    private  int points;//博客点赞数
    private int classify;//博客分类
    private Set<BlogTag> blogTags;
    private  UserToBlog userToBlog;



    @Override
    public String toString() {
        return "Blog{" +
                "bid=" + bid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", link='" + link + '\'' +
                ", points=" + points +
                ", classify=" + classify +
                ", blogTags=" + blogTags +
                ", userToBlog=" + userToBlog +
                '}';
    }



    public Set<BlogTag> getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(Set<BlogTag> blogTags) {
        this.blogTags = blogTags;
    }

    public UserToBlog getUserToBlog() {
        return userToBlog;
    }

    public void setUserToBlog(UserToBlog userToBlog) {
        this.userToBlog = userToBlog;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public Blog() {

    }

    public Blog(int bid, String title, String content, String time, String link, int points, int classify) {
        this.bid = bid;
        this.title = title;
        this.content = content;
        this.time = time;
        this.link = link;
        this.points = points;
        this.classify = classify;
    }
}
