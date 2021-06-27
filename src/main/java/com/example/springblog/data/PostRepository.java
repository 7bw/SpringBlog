package com.example.springblog.data;

import com.example.springblog.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository {

    List<Post> findPosts(long max, int count);

    List<Post> findRecentPosts();

    Post findOne(long id);

    void save(Post post);
}
