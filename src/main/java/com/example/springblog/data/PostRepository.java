package com.example.springblog.data;

public interface PostRepository {

    List<Post> findPosts(long max, int count);
}
