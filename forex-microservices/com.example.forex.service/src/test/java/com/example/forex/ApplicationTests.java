package com.example.forex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.example.forex.controller.ForexController;
import com.example.forex.entity.ExchangeValue;
import com.example.forex.repository.ExchangeValueRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//(SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")

public class ApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ForexController forexController;

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	private ExchangeValueRepository exchangeValueRepository;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(this.forexController).build();
	}

	@Test
	public void testFindByEURToINR() {

		// given
//		ExchangeValue ev = new ExchangeValue(001L, "EUR", "INR", new BigDecimal("80.20"));
//		entityManager.persist(ev);
//		entityManager.flush();
		// when
		ExchangeValue found = exchangeValueRepository.findByFromAndTo("EUR", "INR");
		// then
		assertThat(found.getConversionMultiple().toString()).isEqualTo("75.00");
	}

	@Test
	public void testCallRestAPI() {
		try {

//			final ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
//			objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS); // default is Double
//
//			configJsonProvider(objectMapper);

			mockMvc.perform(get("/currency-exchange/from/EUR/to/INR").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andDo(print())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.conversionMultiple", is(BigDecimal.valueOf(75.00).doubleValue())));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void configJsonProvider(ObjectMapper objectMapper) {

		Configuration.setDefaults(new Configuration.Defaults() {

			private final JsonProvider jsonProvider = new JacksonJsonProvider(objectMapper);
			private final MappingProvider mappingProvider = new JacksonMappingProvider(objectMapper);

			@Override
			public JsonProvider jsonProvider() {
				return jsonProvider;
			}

			@Override
			public MappingProvider mappingProvider() {
				return mappingProvider;
			}

			@Override
			public Set<Option> options() {
				return EnumSet.noneOf(Option.class);
			}
		});
	}
}
