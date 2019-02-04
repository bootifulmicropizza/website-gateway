package com.bootifulmicropizza.websitegateway.rest;

import com.bootifulmicropizza.websitegateway.domain.User;
import com.bootifulmicropizza.websitegateway.rest.request.RegisterRequest;
import com.bootifulmicropizza.websitegateway.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class to test the {@link AccountRestController}.
 */
@WebMvcTest(controllers = AccountRestController.class)
@TestPropertySource("/test.properties")
@RunWith(SpringRunner.class)
public class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private User user;

    @Before
    public void setUp() {
        user = new User(UUID.randomUUID().toString(), "Joe", "Bloggs", "joe@bloggs.com", "letmein");

        when(accountService.register(any(RegisterRequest.class))).thenReturn(user);
    }

    @Test
    public void testRegisterEndpoint() throws Exception {
        mockMvc.perform(
                    post("/account/register/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(asJsonString(user))
                    .with(csrf()))
               .andExpect(status().isCreated())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath(".id").value(user.getId()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
