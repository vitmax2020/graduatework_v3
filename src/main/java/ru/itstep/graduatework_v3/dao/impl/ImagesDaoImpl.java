package ru.itstep.graduatework_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.itstep.graduatework_v3.dao.ImagesDao;
import ru.itstep.graduatework_v3.model.Images;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class ImagesDaoImpl extends JdbcDaoSupport implements ImagesDao {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insertImages(Images img) {
        String sql = "INSERT INTO images " +
                " (postId, userid, imglink, istitle)" +
                " values (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[]{
                img.getPostId(),
                img.getUserId(),
                img.getImgLink(),
                img.getIsTitle()
        });
    }
}
