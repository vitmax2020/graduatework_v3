package ru.itstep.graduatework_v3.dao;

import org.springframework.web.multipart.MultipartFile;
import ru.itstep.graduatework_v3.model.Profile;

public interface ProfileDao {
    void insertProfile(Integer userId);
    void updateProfile(Profile prof, MultipartFile file);
    Profile getProfileByUserId(Integer userId);
}
