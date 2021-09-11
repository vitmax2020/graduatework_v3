package ru.itstep.graduatework_v3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itstep.graduatework_v3.dao.AdminDao;
import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.model.Users;
import ru.itstep.graduatework_v3.service.UsersService;
import ru.itstep.graduatework_v3.utils.WebUtils;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    AdminDao adminDao;

    @Autowired
    UsersService usersService;
    Map<String, Object> admmodel = new HashMap<String, Object>();

    @RequestMapping(value = "inBun", method = RequestMethod.POST)
    public void inBan(Integer userId, Principal principal) {
        adminDao.banUser(userId);
    }
    @RequestMapping(value = "outBun", method = RequestMethod.POST)
    public void outBan(Integer userId, Principal principal) {
        adminDao.antiBanUser(userId);
    }

    @RequestMapping(value = "inAdm", method = RequestMethod.POST)
    public void inAdm(@ModelAttribute("adm") Users usr, Principal principal) {
        Integer userId = usersService.getUserIdByName(usr.getName());
        adminDao.inAdmin(userId);
    }
    @RequestMapping(value = "outAdm", method = RequestMethod.POST)
    public void outAdmm(Integer userId, Principal principal) {
        adminDao.OutAdmin(userId);
    }

    @RequestMapping(value = "inBunBlog", method = RequestMethod.POST)
    public void inBanBlog(Integer userId, Principal principal) {
        adminDao.inBunBlog(userId);
    }
    @RequestMapping(value = "outBunBlog", method = RequestMethod.POST)
    public void outBanBlog(Integer userId, Principal principal) {
        adminDao.outBunBlog(userId);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        Map<String, String> params = new HashMap<String, String>();
        List<Users> userslist1 = new ArrayList<>();
        userslist1 = adminDao.getUsersList();

        admmodel.put("usersList", userslist1);
        admmodel.put("userInfo", userInfo);
        model.addAttribute("admmodel", admmodel);

        return "/admin";
    }
    }
