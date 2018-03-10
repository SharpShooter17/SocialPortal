package com.ujazdowski.SocialPortal;

import com.ujazdowski.SocialPortal.controller.LoginController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class LoginControllerTest {
    @Test
    public void testLoginPage() throws Exception {
        LoginController lc = new LoginController();
        MockMvc mockMvc = standaloneSetup(lc).build();
        mockMvc.perform(get("/")).andExpect(view().name("login"));
    }
}
