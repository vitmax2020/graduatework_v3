package ru.itstep.graduatework_v3.dao;

import ru.itstep.graduatework_v3.model.Users;

import java.util.List;

public interface AdminDao {
    void banUser(Integer userId);
    void antiBanUser(Integer userId);
    void inAdmin(Integer userId);
    void OutAdmin(Integer userId);
    void inBunBlog(Integer userId);
    void outBunBlog(Integer userId);
    List<Users> getUsersList();
}
