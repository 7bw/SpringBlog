package com.example.springblog.web;

import com.example.springblog.User;
import com.example.springblog.data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

    @Test
    public void shouldProcessRegistration() throws Exception {
        UserRepository mockRepository = mock(UserRepository.class);
        User unsaved = new User("bowei4", "24hours", "Bowei", "Li", "bowei4@ualberta.caa");
        User saved = new User(24L, "bowei4", "24hours", "Bowei", "Li", "bowei4@ualberta.ca");
        when(mockRepository.save(unsaved)).thenReturn(saved);

        UserController controller = new UserController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(post("/user/register")
                .param("firstName", "Bowei")
                .param("lastName", "Li")
                .param("username", "bowei4")
                .param("password", "24hours")
                .param("email", "bowei4@ualberta.ca"))
                .andExpect(redirectedUrl("/user/bowei4"));

        verify(mockRepository, atLeastOnce()).save(unsaved);
    }

    @Test
    public void shouldFailValidationWithNoData() throws Exception {
        UserRepository mockRepository = mock(UserRepository.class);
        UserController controller = new UserController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(post("/user/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerForm"))
                .andExpect(model().errorCount(5))
                .andExpect(model().attributeHasFieldErrors(
                        "user", "firstName", "lastName", "username", "password", "email"));
    }


}
