package ru.itstep.graduatework_v3.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.model.Users;
import ru.itstep.graduatework_v3.service.PostsService;
import ru.itstep.graduatework_v3.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itstep.graduatework_v3.utils.WebUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    PostsService postsService;

    @Autowired
    UsersService usersService;

    // @GetMapping(value = {"/", "/index"})
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)

    public String index(Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("userName", principal.getName());
        else
            model.addAttribute("userName", null);

        List<Posts> postslist1 = new ArrayList<>();
        postslist1 = postsService.getAllPosts();

        model.addAttribute("postList", postslist1);
        return "/index";
    }

    //@GetMapping("/login")
    //public String login() {
    //    return "/login";
    // }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "/login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // (1) (en)
        // After user login successfully.
        // (vi)
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        //     System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }


  /*  @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }*/
    //@GetMapping("/admin")
    //public String admin() {
    //     return "/admin";
    //}


  /*  @RequestMapping("/welcome")
    public ModelAndView firstPage() {
        return new ModelAndView("welcome");
    }*/


    @GetMapping("/registration")
    public String register() {
        return "registration";
    }





}
