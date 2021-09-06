package ru.itstep.graduatework_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.itstep.graduatework_v3.dao.CommentsDao;
import ru.itstep.graduatework_v3.model.Comments;
import ru.itstep.graduatework_v3.model.Posts;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Override
    public List<Comments> getCommentsByPostId(Integer postId) throws ParseException {
        String sql = "SELECT * FROM comments WHERE PostId = ? ORDER BY DateCreate desc";
        Object[] params = new Object[]{postId};

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, params);

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<Comments> result = new ArrayList<Comments>();
        for (Map<String, Object> row : rows) {
            Comments com = new Comments();
            com.setPostId((Integer) row.get("PostId"));
            com.setTextComment((String) row.get("TextComment"));
            com.setUserName((String) row.get("username"));
        //    com.setDateCreate(getTimestamp(row.get("DateCreate")).toLocalDateTime()));
        //    com.setDateCreate( df.parse((String) row.get("DateCreate")));
            result.add(com);
        }
        return result;
    }

    @Override
    public Integer getCountCommentsByPostId(Integer PostId) {
        String sql = "SELECT COUNT(CommentId) as comm FROM comments WHERE PostId =?";
        SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, PostId.toString());
        if(rs.first())
            return rs.getInt("comm") ;
        else return 0;
    }
}
