package ru.itstep.graduatework_v3.service;

import ru.itstep.graduatework_v3.model.Rating;

public interface RatingService {
    void insertRating(Rating rtg);
    Integer getCountLikeByPostId(Integer PostId);
    Integer getCountDeslikeByPostId(Integer PostId);


}
