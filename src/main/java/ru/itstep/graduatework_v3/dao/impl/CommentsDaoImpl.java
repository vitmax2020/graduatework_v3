package ru.itstep.graduatework_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.itstep.graduatework_v3.dao.CommentsDao;
import ru.itstep.graduatework_v3.model.Comments;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.Map;

@Repository
public class CommentsDaoImpl extends JdbcDaoSupport implements CommentsDao {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Integer insertComment(Comments com) {
        String sql = "INSERT INTO comments " +
                " (postId, username, email, textComment, dateCreate)" +
                " values (?, ?, ?, ?, ? )";
        getJdbcTemplate().update(sql, new Object[]{
                com.getPostId(),
                com.getUserName(),
                com.getEmail(),
                com.getTextComment(),
                com.getDateCreate()
        });
        Map<String, Object> rowMap = getJdbcTemplate().queryForMap("SELECT LAST_INSERT_ID() as id");
        BigInteger ComId = (java.math.BigInteger) rowMap.get("id");
        return ComId.intValue();
    }

    @Override
    public void deleteComment(Integer CommentID) {

    }
}
