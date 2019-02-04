package com.bootifulmicropizza.websitegateway.service;

import com.bootifulmicropizza.websitegateway.domain.User;
import com.bootifulmicropizza.websitegateway.rest.request.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Service interface for the account microservice.
 */
@FeignClient("account-service")
public interface AccountService {

    /**
     * Handles a request to register a new user.
     *
     * @param request The {@link RegisterRequest} contains the user details
     * @return The created {@link User} instance
     */
    @RequestMapping(method = RequestMethod.GET, value = "/register/")
    User register(@RequestBody final RegisterRequest request);
}
