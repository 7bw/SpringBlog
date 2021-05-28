package com.example.springblog.web;

import com.example.springblog.Post;
import com.example.springblog.data.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PostControllerTest {

    @Test
    public void houldShowRecentSpittles() throws Exception {
        List<Post> expectedPosts = createPostList(20);
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.findPosts(Long.MAX_VALUE, 20))
                .thenReturn(expectedPosts);

        PostController controller = new PostController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/posts.jsp"))
                .build();

        mockMvc.perform(get("/posts"))
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("postList"))
                .andExpect(model().attribute("postList",
                        hasItems(expectedPosts.toArray())));
    }

    private List<Post> createPostList(int count) {
        List<Post> posts = new ArrayList<Post>();
        for (int i=0; i < count; i++) {
            posts.add(new Post("Post " + i, new Date()));
        }
        return posts;
    }
}
