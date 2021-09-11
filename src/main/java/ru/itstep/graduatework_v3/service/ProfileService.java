package ru.itstep.graduatework_v3.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itstep.graduatework_v3.model.Profile;

public interface ProfileService {
    void updateProfile(Profile prof, MultipartFile file);
    void insertProfile(Integer userId);
    Profile getProfileByUserId(Integer userId);
}
