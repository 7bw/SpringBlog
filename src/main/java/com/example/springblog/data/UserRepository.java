package com.example.springblog.data;

import com.example.springblog.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User save(User user);

    User findByUsername(String username);
}
