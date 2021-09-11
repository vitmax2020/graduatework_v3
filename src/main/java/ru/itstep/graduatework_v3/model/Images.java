package ru.itstep.graduatework_v3.model;

public class Images {
    private Integer PostId;
    private Integer UserId;
    private Integer isTitle;
    private String ImgLink;

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

    public Integer getIsTitle() {
        return isTitle;
    }

    public void setIsTitle(Integer isTitle) {
        this.isTitle = isTitle;
    }

    public String getImgLink() {
        return ImgLink;
    }

    public void setImgLink(String imgLink) {
        ImgLink = imgLink;
    }
}
