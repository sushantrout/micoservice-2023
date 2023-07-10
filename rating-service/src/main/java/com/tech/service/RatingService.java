package com.tech.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.entity.Rating;
import com.tech.repository.RatingRepository;

@Service
public class RatingService {
	@Autowired
    private RatingRepository repository;

    public Rating create(Rating rating) {
    	 String randomRatingId = UUID.randomUUID().toString();
    	 rating.setRatingId(randomRatingId);
        return repository.save(rating);
    }

    public List<Rating> getRatings() {
        return repository.findAll();
    }

    public List<Rating> getRatingByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public List<Rating> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
