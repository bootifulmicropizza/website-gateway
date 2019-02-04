package com.bootifulmicropizza.websitegateway.rest;

import com.bootifulmicropizza.websitegateway.annotations.IsCustomer;
import com.bootifulmicropizza.websitegateway.annotations.PublicEndpoint;
import com.bootifulmicropizza.websitegateway.rest.request.RegisterRequest;
import com.bootifulmicropizza.websitegateway.rest.response.Identifier;
import com.bootifulmicropizza.websitegateway.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@IsCustomer
@RequestMapping(value = "/account", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController {

    private AccountService accountService;

    public AccountRestController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PublicEndpoint
    @PostMapping(value = "/register/")
    public ResponseEntity<Identifier> register(@RequestBody final RegisterRequest request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new Identifier(accountService.register(request).getId()));
    }
}
