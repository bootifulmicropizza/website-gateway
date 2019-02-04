package com.bootifulmicropizza.websitegateway.service;

import com.bootifulmicropizza.websitegateway.domain.Catalogue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Service interface for the inventory microservice.
 */
@FeignClient("inventory-service/catalogues")
public interface CatalogueService {

    /**
     * Retrieves the {@link Catalogue} for the given {@code catalogueId}.
     *
     * @param catalogueId The ID of the {@link Catalogue} to retrieve
     * @return {@link Catalogue} instance representing the catalogue
     */
    @RequestMapping(method = RequestMethod.GET, value = "/search/by-catalogue-id")
    Catalogue getCatalogue(@RequestParam("catalogueId") final String catalogueId);
}
