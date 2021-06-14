package com.example.springblog.web;

import com.example.springblog.data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class UserControllerTest {

    @Test
    public void shouldShowRegistration() throws Exception {
        UserRepository mockRepository = mock(UserRepository.class);
        UserController controller = new UserController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/user/register"))
                .andExpect(view().name("registerForm"));
    }
}
