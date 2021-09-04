package ru.itstep.graduatework_v3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itstep.graduatework_v3.dao.CommentsDao;
import ru.itstep.graduatework_v3.model.Comments;
import ru.itstep.graduatework_v3.service.CommentsService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsDao commentsDao;

    @Override
    public Integer insertComment(Comments com) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        java.util.Date sqlDate = cal.getTime();
        com.setDateCreate(sqlDate);
        return commentsDao.insertComment(com);
    }

    @Override
    public void deleteComment(Integer comId) {
        commentsDao.deleteComment(comId);

    }
}