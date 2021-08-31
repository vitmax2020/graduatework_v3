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
    public String processRequest(@ModelAttribute("pst") Posts pst) {
        postsService.insertPosts(pst);
        Posts posts = postsService.getPostsById(pst.getPostId());
        ModelAndView model = new ModelAndView("getPost");
        model.addObject("posts", posts);
        return singlepost(model, pst);
    }

/*    @RequestMapping(value = "/addNewPost", method = RequestMethod.POST)
    public ModelAndView processRequest(@ModelAttribute("pst") Posts pst) {
        postsService.insertPosts(pst);
        List<Posts> posts = postsService.getAllPosts(pst.getUserId());
        ModelAndView model = new ModelAndView("getPost");
        model.addObject("posts", posts);
        return model;
    }*/

    @GetMapping("new-post")
    public String newpost() {
        return "/new-post";
    }

    @GetMapping("posts-list")
    public String postlist(Model model) {
        model.addAttribute("message", "Hello World!");
        return "/posts-list";
    }

    @RequestMapping(value = "single-post", method = RequestMethod.GET)
    public String singlepost(ModelAndView model, Posts pst) {
        //  public ModelAndView processRequest(@ModelAttribute("pst") Posts pst) {
        Posts post;
     //   post = postsService.getPostsById(pst.getPostId());
        //   Model model2 = model.getModel();
        //   ModelAndView model = new ModelAndView("getPost");
        //   model.addObject("posts", pst);

       /* if (post != null)
            model2.addAttribute("postCaption", post.getCaption());
        else
            model2.addAttribute("postCaption", null); */

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
