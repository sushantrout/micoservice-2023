package com.tech.external.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "RATING-SERVICE")
public interface Ratingervice {

}
