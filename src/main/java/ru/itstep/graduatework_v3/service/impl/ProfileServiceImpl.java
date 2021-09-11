package ru.itstep.graduatework_v3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itstep.graduatework_v3.dao.ProfileDao;
import ru.itstep.graduatework_v3.model.Profile;
import ru.itstep.graduatework_v3.service.ProfileService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileDao profileDao;

    @Override
    public void updateProfile(Profile prof, MultipartFile file) {
        profileDao.updateProfile(prof, file);
    }

    @Override
    public void insertProfile(Integer userId) {
        profileDao.insertProfile(userId);
    }

    @Override
    public Profile getProfileByUserId(Integer userId) {
        return profileDao.getProfileByUserId(userId);
    }
}
