package com.example.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.dbservice.model.Quote;
import com.example.stock.dbservice.respository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {

	private QuotesRepository quotesRepository;

	public DBServiceResource(QuotesRepository quotesRepository) {
		this.quotesRepository = quotesRepository;
	}

	@GetMapping("/username")
	public List<String> getQuotes(@PathVariable("username") final String username) {
		return quotesRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quote quotes) {
		return null;
	}

//	@PostMapping("/add")
//	public List<String> add(@RequestBody final Quotes quotes) {
//
//		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(), quote))
//				.forEach(quote -> quotesRepository.save(quote));
//		return getQuotesByUserName(quotes.getUserName());
//	}
}
