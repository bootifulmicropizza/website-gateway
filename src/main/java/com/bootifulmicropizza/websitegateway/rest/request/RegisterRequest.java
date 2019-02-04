package com.bootifulmicropizza.websitegateway.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
