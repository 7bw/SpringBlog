package com.example.springblog.web;

import java.util.Date;
import java.util.List;

import com.example.springblog.data.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import springblog.Post;
import springblog.dataa.PostRepository;

@Controller
@RequestMapping("/posts")
public class PostController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Post> posts(
        @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
        @RequestParam(value="count", defaultValue="20") int count){
        return postRepository.findPosts(max, count);
    }

    @RequestMapping(value="/{postID}", method=RequestMethod.GET)
    public String post(
        @PathVariable("postID") long postID,
        Model model) {
        model.addAttribute(postRepository.findOne(postID));
        return "post";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String savePost(PostForm form, Model model) throws Exception {
        postRepository.save(new Post(null, form.getMessage(), new Date(),
            form.getLongitude(), form.getLatitude()));
        return "redirect:/posts";
    }
}
