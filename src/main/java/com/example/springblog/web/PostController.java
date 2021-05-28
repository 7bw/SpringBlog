package com.example.springblog.web;


import com.example.springblog.data.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String posts(Model model){
        model.addAttribute(
                postRepository.findPosts(Long.MAX_VALUE, 20)
        );
        return "posts";
    }
}
