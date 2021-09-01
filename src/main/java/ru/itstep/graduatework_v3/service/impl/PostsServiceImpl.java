package ru.itstep.graduatework_v3.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itstep.graduatework_v3.dao.PostsDao;
import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itstep.graduatework_v3.service.UsersService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    PostsDao postsDao;
    @Autowired
    private UsersService userDAO;

    @Override
    public Integer insertPosts(Posts pst) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        java.util.Date sqlDate = cal.getTime();
        pst.setDatecreate(sqlDate);

        UserDetails userDetailsImpl = (UserDetails)
                org.springframework.security.core.context.SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
        String userName = userDetailsImpl.getUsername();

        Integer userId = userDAO.getUserIdByName(userName);
        pst.setUserId(userId);
        return postsDao.insertPost(pst);
    }


    @Override
    public void updatePosts(Posts pst) {
        postsDao.updatePost(pst);
    }

    @Override
    public void deletePosts(Integer id) {
        postsDao.deletePost(id);

    }

    @Override
    public Posts getPostsById(Integer id) {
        Posts posts = postsDao.getPostsById(id);
        return posts;

    }

    @Override
    public List<Posts> getAllPosts(Integer userId) {
        return postsDao.getAllPosts(userId);
    }

    @Override
    public List<Posts> getAllPosts() {
        return postsDao.getAllPosts();
    }
}
