package io.javabrains.moviecatalogservice.entity;

import java.util.List;

import lombok.Data;

@Data
public class UserRating {

	private String userID;
	private List<Rating> ratings;

	public UserRating() {
	}

	public UserRating(String userID, List<Rating> ratings) {
		super();
		this.userID = userID;
		this.ratings = ratings;
	}

}
