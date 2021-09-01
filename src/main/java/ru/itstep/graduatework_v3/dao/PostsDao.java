package ru.itstep.graduatework_v3.dao;

import ru.itstep.graduatework_v3.model.Posts;
import ru.itstep.graduatework_v3.model.Users;

import java.util.List;

public interface PostsDao {
    Integer insertPost(Posts pst);
    void updatePost(Posts pst);
    void deletePost(Integer Id);
    Posts getPostsById(Integer Id);
    List<Posts> getAllPosts(Integer userId);
    List<Posts> getAllPosts();

}
