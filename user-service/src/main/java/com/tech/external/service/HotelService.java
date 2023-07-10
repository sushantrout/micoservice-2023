package com.tech.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tech.entity.Hotel;

@FeignClient(name = "HOTEL-SERVICE", path = "/hotels")
public interface HotelService {
	@GetMapping("/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
