package ru.itstep.graduatework_v3.model;

public class Profile {
    private Integer ProfileId;
    private Integer UserId;
    private String Email;
    private String Photo;
    private String Age;
    private String Sex;
    private String DataReg;

    public Integer getProfileId() {
        return ProfileId;
    }

    public void setProfileId(Integer profileId) {
        ProfileId = profileId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getDataReg() {
        return DataReg;
    }

    public void setDataReg(String dataReg) {
        DataReg = dataReg;
    }
}
