package com.bootifulmicropizza.websitegateway;

import com.bootifulmicropizza.websitegateway.service.AccountService;
import com.bootifulmicropizza.websitegateway.service.CatalogueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource("/test.properties")
@SpringBootTest
public class WebsiteGatewayApplicationTests {

	@MockBean
	private CatalogueService catalogueService;

	@MockBean
	private AccountService accountService;

	@Test
	public void contextLoads() {

	}
}
