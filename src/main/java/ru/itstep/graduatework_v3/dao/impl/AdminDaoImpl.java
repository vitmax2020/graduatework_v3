package ru.itstep.graduatework_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.itstep.graduatework_v3.dao.AdminDao;
import ru.itstep.graduatework_v3.model.Comments;
import ru.itstep.graduatework_v3.model.Users;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminDaoImpl extends JdbcDaoSupport implements AdminDao {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void banUser(Integer userId) {
        String sql = "UPDATE users SET isactive = 0 WHERE userid = ? ";
        getJdbcTemplate().update(sql, new Object[]{
                userId});

    }

    @Override
    public void antiBanUser(Integer userId) {
        String sql = "UPDATE users SET isactive = 1 WHERE userid = ? ";
        getJdbcTemplate().update(sql, new Object[]{
                userId});
    }

    @Override
    public void inAdmin(Integer userId) {
        String sql = "UPDATE users SET roule = 2 WHERE userid = ? ";
        getJdbcTemplate().update(sql, new Object[]{
                userId});
    }

    @Override
    public void OutAdmin(Integer userId) {
        String sql = "UPDATE users SET roule = 1 WHERE userid = ? ";
        getJdbcTemplate().update(sql, new Object[]{
                userId});
    }

    @Override
    public void inBunBlog(Integer userId) {
        String sql = "UPDATE posts SET visible = 0 WHERE userid = ? ";
        getJdbcTemplate().update(sql, new Object[]{
                userId});
    }

    @Override
    public void outBunBlog(Integer userId) {
        String sql = "UPDATE posts SET visible = 1 WHERE userid = ? ";
        getJdbcTemplate().update(sql, new Object[]{
                userId});
    }




    @Override
    public List<Users> getUsersList() {
        String sql = "SELECT * FROM users ORDER BY Name";
        SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql);
        List<Users> result = new ArrayList<Users>();
        while (rs.next()) {
            Users usr = new Users();
            usr.setId(rs.getInt("Id"));
            usr.setName(rs.getString("name"));

            result.add(usr);
        }
        return result;
    }

}
