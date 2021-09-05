package ru.itstep.graduatework_v3.dao;

import ru.itstep.graduatework_v3.model.Rating;

public interface RatingDao {
    void insertRating(Rating rtg);
    Integer getRatingCount(Integer PostId);
}
