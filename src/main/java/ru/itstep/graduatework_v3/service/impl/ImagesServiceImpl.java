package ru.itstep.graduatework_v3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itstep.graduatework_v3.dao.ImagesDao;
import ru.itstep.graduatework_v3.model.Images;
import ru.itstep.graduatework_v3.service.ImagesService;

@Service
public class ImagesServiceImpl implements ImagesService {
    @Autowired
    ImagesDao imagesDao;

    @Override
    public void insertImg(Integer postId, Integer userId, String imglink) {
        Images img = new Images();
        img.setPostId(postId);
        img.setUserId(userId);
        img.setImgLink(imglink);
        img.setIsTitle(1);
        imagesDao.insertImages(img);

    }
}
