package ru.itstep.graduatework_v3.service;

import ru.itstep.graduatework_v3.model.Posts;

import java.util.List;

public interface PostsService {
    void insertPosts(Posts pst);
    void updatePosts(Posts pst);
    void deletePosts(Integer id);
    Posts getPostsById(Integer id);
    List<Posts> getAllPosts(Integer userId);
    List<Posts> getAllPosts();
}
