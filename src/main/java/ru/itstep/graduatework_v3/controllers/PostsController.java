package ru.itstep.graduatework_v3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.model.Users;
import ru.itstep.graduatework_v3.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.itstep.graduatework_v3.service.UsersService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostsController {
    @Autowired
    PostsService postsService;

    @Autowired
    UsersService usersService;

    private List<String> postslist = new ArrayList<>();
    private String postCaption;
    Map<String, String> params = new HashMap<String, String>();

    /*  @RequestMapping(value = "/addNewPost", method = RequestMethod.GET)
      public ModelAndView show() {
          return new ModelAndView("Post", "pst", new Posts());
      }
  */
    @RequestMapping(value = "/addNewPost", method = RequestMethod.POST)
    public String addNewPost(Model model, Posts pst) {
        Integer newId = postsService.insertPosts(pst);
        System.out.println("новый ID " + newId);
        Posts posts = postsService.getPostsById(newId);
        if (posts != null)
            postCaption = posts.getCaption();
        else
            System.out.println("не найден пост");

        //  postCaption = posts.getCaption();
        String userName =
                usersService.getUserNameById(posts.getUserId());
        params.put("username", userName);
        params.put("caption", posts.getCaption());
        params.put("text", posts.getText());

        //  String result = restTemplate.getForObject(GET_URL, String.class, params);

        //  postslist.add(model);
        //     model.addAttribute("caption", posts.getCaption());
        // System.out.println("Заголовок "+postCaption);
        //   else
        //       model.addAttribute("caption", null);

        //    ModelAndView model = new ModelAndView("getPost");
        //    model.addObject("posts", posts);
        return "redirect:/single-post";
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
        String userName = usersService.getCurrentUserName();
        Integer userId = usersService.getUserIdByName(userName);
        List<Posts> postslist1 = new ArrayList<>();
        postslist1 = postsService.getAllPosts(userId);

        model.addAttribute("postList", postslist1);
        return "/posts-list";
    }

    @RequestMapping(value ="single-post-id", method = RequestMethod.POST)
    public String processRequest(@ModelAttribute("pst") Posts pst) {
        System.out.println("сюда зашли - контроллер");
        Posts posts = postsService.getPostsById(pst.getPostId());
        if (posts != null)
            postCaption = posts.getCaption();
        else
            System.out.println("не найден пост");
    //    String userName =
     //   usersService.getUserNameById(posts.getUserId());
        params.put("username", posts.getUserName());
        params.put("caption", posts.getCaption());
        params.put("text", posts.getText());

        return "redirect:/single-post";
    }

    @GetMapping("single-post")
    public String singlepost(Model model) {
        model.addAttribute("postInfo", params);
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
