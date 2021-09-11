package ru.itstep.graduatework_v3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itstep.graduatework_v3.dao.RatingDao;
import ru.itstep.graduatework_v3.model.Comments;
import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.model.Rating;
import ru.itstep.graduatework_v3.model.Users;
import ru.itstep.graduatework_v3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

@Controller
public class PostsController {
    @Autowired
    PostsService postsService;

    @Autowired
    UsersService usersService;

    @Autowired
    CommentsService commentsService;

    @Autowired
    RatingService ratingService;

    @Autowired
    ImagesService imagesService;


    private List<String> postslist = new ArrayList<>();
    private String postCaption;

    Map<String, Object> postmodel = new HashMap<String, Object>();
  //  Map<String, Object> mainmodel = new HashMap<String, Object>();

    /*  @RequestMapping(value = "/addNewPost", method = RequestMethod.GET)
      public ModelAndView show() {
          return new ModelAndView("Post", "pst", new Posts());
      }
  */
    @RequestMapping(value = "/addNewPost", method = RequestMethod.POST)
    public String addNewPost(Model model, Posts pst, @RequestParam("file") MultipartFile file, Principal principal) throws ParseException {
        String name = null;
        Integer newId = postsService.insertPosts(pst);
        Posts posts = postsService.getPostsById(newId);

        if (!file.isEmpty()) {
            name = file.getOriginalFilename();
            String ext = FilenameUtils.getExtension(name);
            String RootDir = "";
                    //"C:\\programming\\projects\\graduatework_v3\\src\\main\\resources\\static\\img\\blog-img\\";
            String uploadlin = "localhost/img/blog-img/" +  newId.toString() + "." + ext;
            String FullPath = uploadlin;
                    //+newId.toString() + "." + ext;
            Integer newUserId = posts.getUserId();
            try {
                byte[] bytes = file.getBytes();

                System.out.println("есть файл " + uploadlin);
                File uploadedFile = new File(FullPath);
                BufferedOutputStream stream = null;

                    stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));

                stream.write(bytes);
                stream.flush();
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            imagesService.insertImg(newId, newUserId, uploadlin);
        }

        //  postCaption = posts.getCaption();
/*        String userName =
                usersService.getUserNameById(posts.getUserId());
        postmodel.put("username", userName);
        postmodel.put("caption", posts.getCaption());
        postmodel.put("text", posts.getText());*/

        return processRequest(posts, principal);
        //  String result = restTemplate.getForObject(GET_URL, String.class, params);

        //  postslist.add(model);
        //     model.addAttribute("caption", posts.getCaption());
        // System.out.println("Заголовок "+postCaption);
        //   else
        //       model.addAttribute("caption", null);

        //    ModelAndView model = new ModelAndView("getPost");
        //    model.addObject("posts", posts);
        //   return "redirect:/single-post";
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
    public String newpost(Model model, Principal principal)
    {        if (principal != null) {
        postmodel.put("userName", principal.getName());
        postmodel.put("userRoule", usersService.getUserRole(usersService.getUserIdByName(principal.getName())) );
    }
    else {
        postmodel.put("userName", null);
        postmodel.put("userRoule", null);
    }
        model.addAttribute("mainmodel", postmodel);
        return "/new-post";
    }

    @GetMapping("posts-list")
    public String postlist(Model model, Principal principal) {
        String userName = usersService.getCurrentUserName();
        Integer userId = usersService.getUserIdByName(userName);
        List<Posts> postslist1 = new ArrayList<>();
        postslist1 = postsService.getAllPosts(userId);

        if (principal != null) {
            postmodel.put("userName", principal.getName());
            postmodel.put("userRoule", usersService.getUserRole(usersService.getUserIdByName(principal.getName())) );
        }
        else {
            postmodel.put("userName", null);
            postmodel.put("userRoule", null);
        }
        postmodel.put("postList", postslist1);

        model.addAttribute("mainmodel", postmodel);
        return "/posts-list";
    }

    @RequestMapping(value = "single-post-id", method = RequestMethod.POST)
    public String processRequest(@ModelAttribute("pst") Posts pst, Principal principal) throws ParseException {
        System.out.println("сюда зашли - контроллер");
        Integer postId = pst.getPostId();
        Posts posts = postsService.getPostsById(postId);
        if (posts != null)
            postCaption = posts.getCaption();

        List<Comments> commentslist1 = commentsService.getCommentsByPostId(postId);
        Integer iPostId = posts.getPostId();
        Map<String, String> params = new HashMap<String, String>();
        //    Map<String, String> comms = new HashMap<String, String>();
        //TODO заполняем модель для отдельного поста


        //    params.put("autorizeuser",usersService.getCurrentUserName());
        params.put("postid", iPostId.toString());
        params.put("author", posts.getUserName());
        params.put("caption", posts.getCaption());
        params.put("text", posts.getText());
        params.put("rating", posts.getRating().toString());
        params.put("imglink", posts.getImglink());
        params.put("countlike", ratingService.getCountLikeByPostId(iPostId).toString());
        params.put("countdeslike", ratingService.getCountDeslikeByPostId(iPostId).toString());
        params.put("countcomments", commentsService.getCountCommentsByPostId(iPostId).toString());
/*        //TODO заполняем модель комментариями
        for (Comments i:commentslist1) {
            comms.put("commentsautor", i.getUserName());
            comms.put("commentstext", i.getTextComment());
            System.out.println(i.getUserName());
        }*/
        if (principal != null) {
            postmodel.put("userName", principal.getName());
            postmodel.put("userRoule", usersService.getUserRole(usersService.getUserIdByName(principal.getName())) );
        }
        else {
            postmodel.put("userName", null);
            postmodel.put("userRoule", null);
        }
                    postmodel.put("params", params);
        postmodel.put("comments", commentslist1);
        //+i.getCommentsId().toString()

        return "redirect:/single-post";
    }

    @GetMapping("single-post")
    public String singlepost(Model model) {
        model.addAttribute("mainmodel", postmodel);
        return "/single-post";
    }

    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("com") Comments com, Principal principal) throws ParseException {
        Integer newId = commentsService.insertComment(com);
        Posts pst = postsService.getPostsById(com.getPostId());
        return processRequest(pst, principal);
        //  return "redirect:/single-post";
    }

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public String like(@ModelAttribute("rtg") Rating rtg, Principal principal) throws ParseException {
        String userName = usersService.getCurrentUserName();
        Integer userId = usersService.getUserIdByName(userName);
        rtg.setUserId(userId);
        rtg.setRatingValue(1);
        ratingService.insertRating(rtg);
        Posts pst = postsService.getPostsById(rtg.getPostId());
        return processRequest(pst, principal);
        //     return "redirect:/single-post";
    }

    @RequestMapping(value = "/dislike", method = RequestMethod.POST)
    public String dislike(@ModelAttribute("pst") Rating rtg, Principal principal) throws ParseException {
        String userName = usersService.getCurrentUserName();
        Integer userId = usersService.getUserIdByName(userName);
        rtg.setUserId(userId);
        rtg.setRatingValue(-1);
        ratingService.insertRating(rtg);
        Posts pst = postsService.getPostsById(rtg.getPostId());
        return processRequest(pst, principal);
        //   return "redirect:/single-post";
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
