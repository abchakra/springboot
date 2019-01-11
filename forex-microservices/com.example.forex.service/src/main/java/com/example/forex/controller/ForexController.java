package com.example.forex.controller;

import static com.example.forex.entity.Converter.fromJsonString;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.forex.Application;
import com.example.forex.entity.ExchangeValue;
import com.example.forex.entity.Quote;
import com.example.forex.repository.ExchangeValueRepository;

@RestController
public class ForexController {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
	}

	@GetMapping(path = "/currency-exchange/{CODE}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Quote getLiveExchangeRate(@PathVariable String CODE) {
		final String uri = "https://www.freeforexapi.com/api/live?pairs=" + CODE;

		RestTemplate restTemplate = new RestTemplate();
		String quoteJSON = restTemplate.getForObject(uri, String.class);
		log.info(quoteJSON);

		try {
			Quote quote = fromJsonString(quoteJSON);
			return quote;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

// https://www.freeforexapi.com/api/live
}