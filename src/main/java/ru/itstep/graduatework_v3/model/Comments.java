package ru.itstep.graduatework_v3.model;

import java.util.Date;

public class Comments {
    private Integer CommentsId;
    private Integer PostId;
    private String UserName;
    private String Email;
    private Date DateCreate;
    private String TextComment;

    public Integer getCommentsId() {
        return CommentsId;
    }

    public void setCommentsId(Integer commentsId) {
        CommentsId = commentsId;
    }

    public Integer getPostId() {
        return PostId;
    }

    public void setPostId(Integer postId) {
        PostId = postId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        DateCreate = dateCreate;
    }

    public String getTextComment() {
        return TextComment;
    }

    public void setTextComment(String textComment) {
        TextComment = textComment;
    }
}
