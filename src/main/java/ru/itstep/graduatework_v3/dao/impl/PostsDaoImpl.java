package ru.itstep.graduatework_v3.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.itstep.graduatework_v3.dao.PostsDao;
import ru.itstep.graduatework_v3.mappers.PostsMapper;
import ru.itstep.graduatework_v3.mappers.UsersMapper;
import ru.itstep.graduatework_v3.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.itstep.graduatework_v3.model.Users;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//import java.util.*;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class PostsDaoImpl extends JdbcDaoSupport implements PostsDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Integer insertPost(Posts pst) {

        String sql = "INSERT INTO posts " +
                "(caption, text, userid, Rating, Visible, datecreate) VALUES (?, ?, ?, 0, 1,?)";
        getJdbcTemplate().update(sql, new Object[]{
                pst.getCaption(),
                pst.getText(),
                pst.getUserId(),
                pst.getDatecreate()
        });
        Map<String, Object> rowMap = getJdbcTemplate().queryForMap("SELECT LAST_INSERT_ID() as id");
        BigInteger PostId = (java.math.BigInteger) rowMap.get("id");
        return PostId.intValue();

    }

    @Override
    public void updatePost(Posts pst) {
        String sql = "";
    }

    @Override
    public void deletePost(Integer Id) {
        String sql = "";
    }

    @Override
    public Posts getPostsById(Integer id) {
        String sql = "SELECT * FROM posts p WHERE PostId = ?";
        Object[] params = new Object[]{id};
        PostsMapper mapper = new PostsMapper();
        try {
            Posts postInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
            return postInfo;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
       /* //new Object[]{Id},
        return (Posts) getJdbcTemplate().queryForObject(sql, (rs, rwNumber) -> {
            Posts pst = new Posts(id);
            //   emp.setPostId(rs.getInt ("PostId"));
            pst.setCaption(rs.getString("Caption"));
            pst.setText(rs.getString("Text"));
            pst.setUserId(rs.getInt("UserId"));
            pst.setRating(rs.getInt("Rating"));
            pst.setVisible(rs.getBoolean("Visible"));
            //      pst.setUserName(rs.getString("Text"));

            return pst;
        });*/


    @Override
    public List<Posts> getAllPosts(Integer userId) {
        String sql = "SELECT p.*, u.name as username FROM posts p, users u " +
                " where u.id = p.UserId " +
                "and visible = 1 and UserId=? order by datecreate";

       Object[] params = new Object[]{userId};

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, params);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<Posts> result = new ArrayList<Posts>();
        for (Map<String, Object> row : rows) {
            Posts pst = new Posts();
            pst.setPostId((Integer) row.get("PostId"));
            pst.setCaption((String) row.get("Caption"));
            pst.setText((String) row.get("Text"));
            pst.setRating((Integer) row.get("Rating"));
            pst.setUserId((Integer) row.get("UserId"));
     //       pst.setVisible((Integer) row.get("Visible"));
            //    java.sql.Date SqlDate = java.sql.Date.valueOf((String) row.get("Datecreate"));
            //     pst.setDatecreate(new java.util.Date(SqlDate.getTime()));
            pst.setUserName((String) row.get("username"));
            result.add(pst);
        }
        return result;
    }

    @Override
    public List<Posts> getAllPosts() {
        String sql = "SELECT *, u.name as username FROM posts p, users u " +
                " where u.id = p.UserId " +
                "and visible = 1 order by datecreate";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<Posts> result = new ArrayList<Posts>();
        for (Map<String, Object> row : rows) {
            Posts pst = new Posts();
            pst.setCaption((String) row.get("Caption"));
            pst.setRating((Integer) row.get("Rating"));
            pst.setUserId((Integer) row.get("UserId"));
            pst.setVisible((Integer) row.get("Visible"));
            //    java.sql.Date SqlDate = java.sql.Date.valueOf((String) row.get("Datecreate"));
            //     pst.setDatecreate(new java.util.Date(SqlDate.getTime()));
            pst.setUserName((String) row.get("username"));
            result.add(pst);
        }
        return result;
    }
}