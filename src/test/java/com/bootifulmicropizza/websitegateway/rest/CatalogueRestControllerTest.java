package com.bootifulmicropizza.websitegateway.rest;

import com.bootifulmicropizza.websitegateway.domain.Catalogue;
import com.bootifulmicropizza.websitegateway.domain.Product;
import com.bootifulmicropizza.websitegateway.service.CatalogueService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class to test the {@link CatalogueRestController}.
 */
@WebMvcTest(controllers = CatalogueRestController.class)
@TestPropertySource("/test.properties")
@RunWith(SpringRunner.class)
public class CatalogueRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogueService catalogueService;

    @Before
    public void setUp() {
        final Product product = createTestProduct();

        final Set<Product> products = new HashSet<>();
        products.add(product);
        final Catalogue catalogue = new Catalogue(UUID.randomUUID().toString(), "Pizzas", products);
        when(catalogueService.getCatalogue(any())).thenReturn(catalogue);
    }

    @Test
    public void testGetPizzaCatalogueWithNoUser() throws Exception {
        mockMvc.perform(get("/catalogue/pizza/"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("products.[0].name").value("Farm House"));
    }

    @Test
    @WithAnonymousUser
    public void testGetPizzaCatalogueWithAnonUser() throws Exception {
        mockMvc.perform(get("/catalogue/pizza/"))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(status().isOk())
               .andExpect(jsonPath("products.[0].name").value("Farm House"));
    }

    @Test
    @WithMockUser(username = "joe.bloggs", roles = { "CUSTOMER" })
    public void testGetPizzaCatalogueWithAuthUser() throws Exception {
        mockMvc.perform(get("/catalogue/pizza/"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("products.[0].name").value("Farm House"));
    }

    @Test
    @WithAnonymousUser
    public void testPrivateMethodWithAnonUser() throws Exception {
        mockMvc.perform(get("/catalogue/private/"))
               .andExpect(status().isForbidden());
    }

    @Test
    public void testPrivateMethodWithNoUser() throws Exception {
        mockMvc.perform(get("/catalogue/private/"))
               .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "joe.bloggs", roles = { "CUSTOMER" })
    public void testPrivateMethodWithAuthUser() throws Exception {
        mockMvc.perform(get("/catalogue/private/"))
               .andExpect(status().isOk());
    }

    private Product createTestProduct() {
        return new Product(UUID.randomUUID().toString(), "Farm House", new BigDecimal(5.99));
    }
}
