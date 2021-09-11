package ru.itstep.graduatework_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.itstep.graduatework_v3.dao.ProfileDao;
import ru.itstep.graduatework_v3.mappers.PostsMapper;
import ru.itstep.graduatework_v3.mappers.ProfileMapper;
import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.model.Profile;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.awt.*;
import java.io.*;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Repository
public class ProfileDaoImpl extends JdbcDaoSupport implements ProfileDao {

    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insertProfile(Integer userId) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        java.util.Date sqlDate = cal.getTime();

        in.addValue("userId", userId);
        in.addValue("datareg", sqlDate);

        String sql = "INSERT INTO profile (userId, DataReg) VALUES (:userId, :datareg)";

        NamedParameterJdbcTemplate jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplateObject.update(sql, in);
    }

    @Override
    public void updateProfile(Profile prof, MultipartFile file) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        if (!("".equals(file.getOriginalFilename()))) {
    //        File f = new File(file.getOriginalFilename());

            try {
                byte[] bytes = file.getBytes();

                in.addValue("userId", 2);
                in.addValue("photo", new SqlLobValue(new ByteArrayInputStream(bytes),
                        bytes.length, new DefaultLobHandler()), Types.BLOB);
                in.addValue("datareg", prof.getDataReg());

                String sql = "INSERT INTO profile (userId, photo, DataReg) VALUES (:userId, :photo, :datareg)";

                NamedParameterJdbcTemplate jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
                jdbcTemplateObject.update(sql, in);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Profile getProfileByUserId(Integer userId) {
        String sql = "SELECT p.* " +
                " FROM profile p" +
                " where p.UserId = ?" ;

        Object[] params = new Object[]{userId};
        ProfileMapper mapper = new ProfileMapper();
        try {
            Profile profInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
            return profInfo;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Ошибка в getProfileByUserId: "+e.getMessage());
            return null;
        }
    }
}
