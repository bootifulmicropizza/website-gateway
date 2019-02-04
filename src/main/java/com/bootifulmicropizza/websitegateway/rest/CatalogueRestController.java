package com.bootifulmicropizza.websitegateway.rest;

import com.bootifulmicropizza.websitegateway.annotations.IsCustomer;
import com.bootifulmicropizza.websitegateway.annotations.PublicEndpoint;
import com.bootifulmicropizza.websitegateway.domain.Catalogue;
import com.bootifulmicropizza.websitegateway.service.CatalogueService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@IsCustomer
@RequestMapping(value ="/catalogue", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatalogueRestController {

    private CatalogueService catalogueService;

    public CatalogueRestController(final CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @PublicEndpoint
    @RequestMapping(value = "/pizza/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Catalogue> getPizzaCatalogue() {
        return ResponseEntity.ok(catalogueService.getCatalogue("PIZZAS"));
    }

    @PublicEndpoint
    @RequestMapping(value = "/side/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Catalogue> getSideCatalogue() {
        return ResponseEntity.ok(catalogueService.getCatalogue("SIDES"));
    }

    @PublicEndpoint
    @RequestMapping(value = "/dessert/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Catalogue> getDessertCatalogue() {
        return ResponseEntity.ok(catalogueService.getCatalogue("DESSERTS"));
    }

    @PublicEndpoint
    @RequestMapping(value = "/drink/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Catalogue> getDrinkCatalogue() {
        return ResponseEntity.ok(catalogueService.getCatalogue("DRINKS"));
    }

    @RequestMapping("/private")
    @IsCustomer
    public ResponseEntity<String> privateMethod() {
        return ResponseEntity.ok("ok");
    }
}
