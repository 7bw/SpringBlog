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
    public void shouldShowRecentPosts() throws Exception {
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

    @Test
    public void shouldShowPagedPosts() throws Exception {
        List<Post> expectedPosts = createPostList(50);
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.findPosts(238900, 50))
            .thenReturn(expectedPosts);
        
        PostController controller = new PostController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
            .setSingleView(new InternalResourceView("/WEB-INF/views/posts.jsp"))
            .build();

        mockMvc.perform(get("/posts?max=238900&count=50"))
          .andExpect(view().name("posts"))
          .andExpect(model().attributeExists("postList"))
          .andExpect(model().attribute("postList", 
                     hasItems(expectedPosts.toArray())));
    }

    @Test
    public void testPost() throws Exception {
        Post expectedPost = new Post("Hello", new Date());
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.findOne(12345)).thenReturn(expectedPost);
        
        PostController controller = new PostController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/posts/12345"))
          .andExpect(view().name("post"))
          .andExpect(model().attributeExists("post"))
          .andExpect(model().attribute("post", expectedPost));
    }

    @Test
    public void savePost() throws Exception {
        PostRepository mockRepository = mock(PostRepository.class);
        PostController controller = new PostController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(post("/posts")
               .param("message", "Hello World") // this works, but isn't really testing what really happens
               .param("longitude", "-81.5811668")
               .param("latitude", "28.4159649")
               )
               .andExpect(redirectedUrl("/posts"));
        
        verify(mockRepository, atLeastOnce()).save(new Post(null, "Hello World", new Date(), -81.5811668, 28.4159649));
    }

    private List<Post> createPostList(int count) {
        List<Post> posts = new ArrayList<Post>();
        for (int i=0; i < count; i++) {
            posts.add(new Post("Post " + i, new Date()));
        }
        return posts;
    }
}
