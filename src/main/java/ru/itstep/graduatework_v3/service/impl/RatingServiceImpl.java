package ru.itstep.graduatework_v3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.itstep.graduatework_v3.dao.RatingDao;
import ru.itstep.graduatework_v3.model.Rating;
import ru.itstep.graduatework_v3.service.RatingService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingDao ratingDao;

    @Override
    public void insertRating(Rating rtg) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        java.util.Date sqlDate = cal.getTime();
        rtg.setDateCreate(sqlDate);
        try{
        ratingDao.insertRating(rtg);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getCountLikeByPostId(Integer PostId) {
        return ratingDao.getCountLikeByPostId(PostId);
    }

    @Override
    public Integer getCountDeslikeByPostId(Integer PostId) {
        return ratingDao.getCountDeslikeByPostId(PostId);
    }
}
