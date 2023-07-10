package com.tech.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tech.entity.Hotel;
import com.tech.entity.Rating;
import com.tech.entity.User;
import com.tech.external.service.HotelService;
import com.tech.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService; 

	/*
	 * first approach String ratingIdURLTemplate =
	 * "http://localhost:8083/ratings/users/%s"; String hotelUrlTemplate =
	 * "http://localhost:8082/hotels/%s";
	 */

	String ratingIdURLTemplate = "http://RATING-SERVICE/ratings/users/%s";
	String hotelUrlTemplate = "http://HOTEL-SERVICE/hotels/%s";

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {

		User user = userService.getUser(userId);

		List<Rating> forEntity = getUserRatingByUserUdUsinghardcodedAddress(userId);
		user.setRatings(forEntity);
		return ResponseEntity.ok(user);
	}

	private List<Rating> getUserRatingByUserUdUsinghardcodedAddress(String userId) {
		String ratingByuserIdURL = String.format(ratingIdURLTemplate, userId);
		Rating[] ratingArray = restTemplate.getForObject(ratingByuserIdURL, Rating[].class);

		return Arrays.stream(ratingArray).map(e -> {
			//String hotelByHotelId = String.format(hotelUrlTemplate, e.getHotelId());
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(hotelByHotelId, Hotel.class);
			//Hotel hotel = forEntity.getBody();
			Hotel hotel = hotelService.getHotel(e.getHotelId());
			e.setHotel(hotel);
			return e;
		}).collect(Collectors.toList());
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.getAllUser();
		for (User user : allUser) {
			List<Rating> userRatingByUserUdUsinghardcodedAddress = getUserRatingByUserUdUsinghardcodedAddress(
					user.getUserId());
			user.setRatings(userRatingByUserUdUsinghardcodedAddress);
		}
		return ResponseEntity.ok(allUser);
	}
}