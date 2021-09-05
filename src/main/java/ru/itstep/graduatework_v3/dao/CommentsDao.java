package ru.itstep.graduatework_v3.dao;

import ru.itstep.graduatework_v3.model.Comments;
import ru.itstep.graduatework_v3.model.Posts;

import java.util.List;

public interface  CommentsDao {
    Integer insertComment(Comments com);
    void deleteComment(Integer CommentID);
    List<Comments> getCommentsByPostId(Integer postId);

}
