package ru.itstep.graduatework_v3.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PostsMapper implements RowMapper<Posts> {
    public static final String Posts_SQL
            = "Select p.PostId, p.UserId, p.Caption, p.Text, p.Rating, p.Visible, p.DateCreate From Posts p ";

    @Override
    public Posts mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer postid = rs.getInt("PostId");
        Integer userid = rs.getInt("UserId");
        String caption = rs.getString("Caption");
        String text = rs.getString("Text");
        Integer rating = rs.getInt("Rating");
        Integer visible = rs.getInt("Visible");
        Date datecreate = rs.getDate("DateCreate");
        String username = rs.getString("UserName");
        String imglink = rs.getString("Imglink");

        return new Posts(postid, userid, caption, text, rating, visible, datecreate, username, imglink);
    }
}
