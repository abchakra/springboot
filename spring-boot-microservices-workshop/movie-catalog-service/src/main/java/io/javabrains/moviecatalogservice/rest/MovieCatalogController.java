package io.javabrains.moviecatalogservice.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatalogservice.entity.CatalogItem;
import io.javabrains.moviecatalogservice.entity.MovieInfo;
import io.javabrains.moviecatalogservice.entity.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getMovieCatalog(@PathVariable("userId") String userID) {
//		return Collections.+(new CatalogItem("Titanic", "Drowning ship with stupid rich people", 4));
//		List<Rating> ratingsList = Arrays.asList(new Rating("1234", 3), new Rating("5678", 4));

		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratings/users/" + userID,
				UserRating.class);

		return userRating.getRatings().stream().map(rating -> {

			MovieInfo movieInfo = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieID(),
					MovieInfo.class);
			return new CatalogItem(movieInfo.getName(), "", rating.getRating());

		}).collect(Collectors.toList());

	}
}
