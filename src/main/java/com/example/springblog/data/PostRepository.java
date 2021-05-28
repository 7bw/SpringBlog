package com.example.springblog.data;

import com.example.springblog.Post;

import java.util.List;

public interface PostRepository {

    List<Post> findPosts(long max, int count);
}
