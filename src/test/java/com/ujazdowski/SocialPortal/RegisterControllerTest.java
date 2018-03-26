package com.ujazdowski.SocialPortal;

import com.ujazdowski.SocialPortal.controller.RegisterController;
import com.ujazdowski.SocialPortal.service.UserService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class RegisterControllerTest {

    @Test
    public void testLoginPage() throws Exception {
       /* UserService userService = mock(UserService.class);

        RegisterController rc = new RegisterController(userService, languagesRepository);
        MockMvc mockMvc = standaloneSetup(rc).build();
        mockMvc.perform(get("/Register")).andExpect(view().name("register"));*/
    }
}
