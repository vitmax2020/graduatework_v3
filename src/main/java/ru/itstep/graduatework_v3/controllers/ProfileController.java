package ru.itstep.graduatework_v3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itstep.graduatework_v3.model.Profile;
import ru.itstep.graduatework_v3.service.ProfileService;
import ru.itstep.graduatework_v3.service.UsersService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProfileController {
    @Autowired
    ProfileService profileService;
    @Autowired
    UsersService usersService;

    Map<String, Object> mainmodel = new HashMap<String, Object>();
    Map<String, Object> profmodel = new HashMap<String, Object>();


    @RequestMapping(value = "/addNewProfile", method = RequestMethod.POST)
    public String addNewProfile(Model model, Profile prof, @RequestParam("file") MultipartFile file, Principal principal) throws ParseException {
        String name = null;
        profileService.updateProfile(prof, file);
return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("mainmodel", profmodel);
        return "/profile";
    }

    @RequestMapping(value = "single-profile-id", method = RequestMethod.GET)
    public String RequestProfile(@ModelAttribute("prof") Profile prof, Principal principal) {
        System.out.println("сюда зашли - контроллер");
        Integer userId = prof.getUserId();
        Map<String, String> params = new HashMap<String, String>();
        Profile profile = profileService.getProfileByUserId(userId);

        if (principal != null) {
            profmodel.put("userName", principal.getName());
            profmodel.put("userRoule", usersService.getUserRole(usersService.getUserIdByName(principal.getName())));
        } else {
            profmodel.put("userName", null);
            profmodel.put("userRoule", null);
        }
        params.put("userId", profile.getUserId().toString());
        params.put("email", profile.getEmail());
        InputStream is=null;

        is = new ByteArrayInputStream(profile.getPhoto());

                Image img = null;
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        profmodel.put("photo", img);
        profmodel.put("params", params);
      //  params.put("photo", profile.getUserId().toString());
        return "redirect:/profile";
    }

}
