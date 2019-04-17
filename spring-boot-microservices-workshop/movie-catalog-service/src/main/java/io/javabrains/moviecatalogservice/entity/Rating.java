package io.javabrains.moviecatalogservice.entity;

import lombok.Data;

@Data
public class Rating {

	private String movieID;
	private int rating;

	public Rating() {
	}
	
	public Rating(String movieID, int rating) {
		this.movieID = movieID;
		this.rating = rating;
	}

}
