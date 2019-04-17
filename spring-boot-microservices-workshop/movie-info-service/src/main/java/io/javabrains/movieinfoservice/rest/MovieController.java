package io.javabrains.movieinfoservice.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.movieinfoservice.entity.MovieInfo;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@RequestMapping("/{id}")
	public MovieInfo getMovie(@PathVariable("id") String movieId) {
		return new MovieInfo(movieId, "Titanic");
	}
}
