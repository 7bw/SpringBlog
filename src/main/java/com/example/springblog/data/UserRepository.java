package com.example.springblog.data;

import com.example.springblog.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);
}
