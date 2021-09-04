package ru.itstep.graduatework_v3.service;

import ru.itstep.graduatework_v3.model.Comments;

public interface CommentsService {
    Integer insertComment(Comments com);
    void deleteComment(Integer comId);
}
