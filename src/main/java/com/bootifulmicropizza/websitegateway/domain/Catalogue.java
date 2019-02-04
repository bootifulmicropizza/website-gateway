package com.bootifulmicropizza.websitegateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Catalogue {

    private String id;

    private String name;

    private Set<Product> products;
}
