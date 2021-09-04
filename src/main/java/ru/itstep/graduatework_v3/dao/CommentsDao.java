package ru.itstep.graduatework_v3.dao;

import ru.itstep.graduatework_v3.model.Comments;

public interface  CommentsDao {
    Integer insertComment(Comments com);
    void deleteComment(Integer CommentID);

}
