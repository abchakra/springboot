package io.javabrains.movieinfoservice.entity;

import lombok.Data;

@Data
public class MovieInfo {

	private String id;
	private String name;

	public MovieInfo() {
	}

	public MovieInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}

}
