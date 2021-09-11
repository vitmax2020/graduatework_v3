package ru.itstep.graduatework_v3.service;

import ru.itstep.graduatework_v3.model.Images;

public interface ImagesService {
    void insertImg(Integer postId, Integer userId, String imglink);
}
