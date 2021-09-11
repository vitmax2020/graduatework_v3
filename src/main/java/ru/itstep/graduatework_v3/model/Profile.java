package ru.itstep.graduatework_v3.model;

import java.io.InputStream;
import java.util.Date;

public class Profile {
    private Integer ProfileId;
    private Integer UserId;
    private String Email;
    private byte[] Photo;
    private Date Age;
    private String Sex;
    private Date DataReg;

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

    public byte[] getPhoto() {
        return Photo;
    }

    public void setPhoto(byte[] photo) {
        Photo = photo;
    }

    public Date getAge() {
        return Age;
    }

    public void setAge(Date age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public Date getDataReg() {
        return DataReg;
    }

    public void setDataReg(Date dataReg) {
        DataReg = dataReg;
    }
}
