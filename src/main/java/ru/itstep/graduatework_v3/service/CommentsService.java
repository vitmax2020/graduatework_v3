package ru.itstep.graduatework_v3.service;

import ru.itstep.graduatework_v3.model.Comments;
import ru.itstep.graduatework_v3.model.Posts;

import java.text.ParseException;
import java.util.List;

public interface CommentsService {
    Integer insertComment(Comments com);
    void deleteComment(Integer comId);
    List<Comments> getCommentsByPostId(Integer postId) throws ParseException;
}
