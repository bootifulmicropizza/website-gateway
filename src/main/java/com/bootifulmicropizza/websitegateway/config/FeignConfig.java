package com.bootifulmicropizza.websitegateway.config;

import com.bootifulmicropizza.websitegateway.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.bootifulmicropizza.websitegateway.service")
public class FeignConfig {

    @Bean
    public RequestInterceptor getUserFeignClientInterceptor() {
        return new FeignRequestInterceptor();
    }
}
