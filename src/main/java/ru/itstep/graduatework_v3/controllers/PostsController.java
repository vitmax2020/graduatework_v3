package ru.itstep.graduatework_v3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.model.Users;
import ru.itstep.graduatework_v3.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostsController {
    @Autowired
    PostsService postsService;

    @RequestMapping(value = "/addNewPost", method = RequestMethod.GET)
    public ModelAndView show() {
        return new ModelAndView("Post", "pst", new Posts());
    }

    @RequestMapping(value = "/addNewPost", method = RequestMethod.POST)
    public ModelAndView processRequest(@ModelAttribute("pst") Posts pst) {
        postsService.insertPosts(pst);
        List<Posts> posts = postsService.getAllPosts(pst.getUserId());
        ModelAndView model = new ModelAndView("getPost");
        model.addObject("posts", posts);
        return model;
    }


    @RequestMapping(value = "/single-post", method = RequestMethod.GET)
    public String singlepost(Model model, Posts pst) {
        //  public ModelAndView processRequest(@ModelAttribute("pst") Posts pst) {
        Posts post;
        post = postsService.getPostsById(pst.getPostId());
        //   Model model = new Model();
        //   ModelAndView model = new ModelAndView("getPost");
        //   model.addObject("posts", pst);
        if (post != null)
            model.addAttribute("postCaption", post.getCaption());
        else
            model.addAttribute("postCaption", null);

        return "/single-post";

    }

/*    @RequestMapping("/posts-list")
    class PersonController {

        @GetMapping("/{id}")
        public List<Posts> posts = postsService.getAllPosts(@PathVariable Long id){

        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public void add(@RequestBody Person person) {
            // ...
        }
    }

    @GetMapping("posts-list")
    public String postlist(Model model) {
        model.addAttribute("message", "Hello World!");
        return "posts-list";
    }


    @RequestMapping(value = "/posts-list", method = RequestMethod.POST)
    public ModelAndView processRequest(@ModelAttribute("pst") Posts pst) {
        List<Posts> posts = postsService.getAllPosts(pst.getUserId());
        ModelAndView model = new ModelAndView("getPost");
        model.addObject("posts", posts);
        return model;
    }
    //    return "/single-post";

    }*/

}
