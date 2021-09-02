package ru.itstep.graduatework_v3.model;

//import java.sql.Date;

import java.util.Date;

public class Posts {
    private Integer PostId;
    private Integer UserId;
    private String UserName;
    private String caption;
    private String text;
    private Integer rating;
    private Integer visible;
    private Date datecreate;

    public Posts(Integer postid, String caption, String text, Integer rating, Integer visible, Date datecreate) {
        this.setPostId(postid);
        this.setCaption(caption);
        this.setText(text);
        this.setRating(rating);
        this.setVisible(visible);
        this.setDatecreate(datecreate);
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public Posts(Integer id) {
        this.setPostId(id);
    }

    public Posts() {

    }

    public Integer getPostId() {
        return PostId;
    }

    public void setPostId(Integer postId) {
        PostId = postId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
