package io.javabrains.ratingsdataservice.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingsdataservice.entity.Rating;
import io.javabrains.ratingsdataservice.entity.UserRating;

@RestController
@RequestMapping("ratings")
public class RatingController {

	@RequestMapping("{ratingId}")
	public Rating getRating(@PathVariable("ratingId") String ratingId) {

		return new Rating(ratingId, 4);
	}

	@RequestMapping("users/{userID}")
	public UserRating getRatings(@PathVariable("userID") String userID) {
		List<Rating> ratingsList = Arrays.asList(new Rating("1234", 3), new Rating("5678", 4));

		return new UserRating(userID, ratingsList);

	}

}
