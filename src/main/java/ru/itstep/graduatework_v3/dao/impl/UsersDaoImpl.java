package ru.itstep.graduatework_v3.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.itstep.graduatework_v3.mappers.UsersMapper;
import ru.itstep.graduatework_v3.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import ru.itstep.graduatework_v3.dao.UsersDao;

@Repository
public class UsersDaoImpl extends JdbcDaoSupport implements UsersDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insertUser(Users user) {

        String sql = "INSERT INTO users " +
                "( Name, password, roule, isactive) VALUES (?, ?, 1, 1)";
        getJdbcTemplate().update(sql, new Object[]{
                user.getName(),
                user.getPassword()
        });
    }

    @Override
    public void updateUser(Users user) {
        String sql = "UPDATE users SET " +
                "Name = ?, password = ?, roule = ?, isactive = ?" +
                "where Id = ?";
        getJdbcTemplate().update(sql, new Object[]{
                user.getName(),
                user.getPassword(),
                user.getRoule(),
                user.getIsactive(),

                user.getId()
        });
    }

    @Override
    public void deleteUser(Integer Id) {
        String sql = "DELETE FROM users WHERE id = ?";
    }

    @Override
    public void insertUsers(final List<Users> users) {
        String sql = "INSERT INTO users " + "(Id, Name, password, roule, isactive) VALUES (?, ?, ?, ?, 1)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Users user = users.get(i);
                ps.setInt(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                ps.setInt(4, user.getRoule());

            }

            public int getBatchSize() {
                return users.size();
            }
        });

    }

    @Override
    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Users> result = new ArrayList<Users>();
        for (Map<String, Object> row : rows) {
            Users emp = new Users();
            emp.setId((Integer) row.get("Id"));
            emp.setName((String) row.get("Name"));
            emp.setPassword((String) row.get("Password"));
            emp.setRoule((Integer) row.get("Roule"));

            result.add(emp);
        }

        return result;
    }

    @Override
    public Users getUsersById(Integer Id) {
        String sql = "SELECT * FROM users WHERE Id = ?";

        Object[] params = new Object[]{Id};
        UsersMapper mapper = new UsersMapper();
        try {
            Users userInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
            //.queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Users getUsersByName(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = "SELECT * from users where isactive = 1 and name = ?";

        Object[] params = new Object[]{userName};
        UsersMapper mapper = new UsersMapper();
        try {
            Users userInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
            //         Users userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public Integer getUserRole(Integer id) {
        String sql = "SELECT * from users u where u.id = ?";

        Object[] params = new Object[]{id};
        UsersMapper mapper = new UsersMapper();
        try {
            Users userInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
            //      Users userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            Integer userRole = userInfo.getRoule();

            return userRole;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer getUserId(String userName) {
        String sql = "SELECT * from users u where u.name = ?";

        Object[] params = new Object[]{userName};
        UsersMapper mapper = new UsersMapper();
        System.out.println("userName - " + userName);
        try {
            Users userInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
            Integer userId = userInfo.getId();

            return userId;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}