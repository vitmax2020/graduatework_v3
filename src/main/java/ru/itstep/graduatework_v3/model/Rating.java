package ru.itstep.graduatework_v3.model;

import java.util.Date;

public class Rating {
    private Integer RatingId;
    private Integer PostId;
    private Integer UserId;
    private Integer ReatingValue;
    private Date DateCreate;

    public Integer getRatingId() {
        return RatingId;
    }

    public void setRatingId(Integer ratingId) {
        RatingId = ratingId;
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

    public Integer getReatingValue() {
        return ReatingValue;
    }

    public void setReatingValue(Integer reatingValue) {
        ReatingValue = reatingValue;
    }

    public Date getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        DateCreate = dateCreate;
    }
}
