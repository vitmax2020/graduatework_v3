package ru.itstep.graduatework_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.itstep.graduatework_v3.dao.RatingDao;
import ru.itstep.graduatework_v3.model.Rating;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.text.ParseException;

@Repository
public class RatingDaoImpl extends JdbcDaoSupport implements RatingDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insertRating(Rating rtg) throws DataAccessException {
        String sql = "INSERT INTO rating (PostId, UserId, RatingValue, DateCreate)" +
                " VALUES (?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[]{
                rtg.getPostId(),
                rtg.getUserId(),
                rtg.getRatingValue(),
                rtg.getDateCreate()
        });
    }

    @Override
    public Integer getRatingCount(Integer PostId) {
        String sql = "SELECT COUNT(RatingId) as rat FROM rating WHERE PostId =?";
        SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, PostId.toString());
        if(rs.first())
        return rs.getInt("rat") ;
        else return 0;
    }
}
