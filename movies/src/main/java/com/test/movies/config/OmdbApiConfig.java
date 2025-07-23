package com.test.movies.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class OmdbApiConfig {

	@Bean
	public WebClient omdbWebClient(
			@Value("${omdb.base-url}") String baseUrl,
			@Value("${omdb.timeout:5s}") Duration timeout,
			@Value("${omdb.api-key}") String apiKey) {

		HttpClient httpClient = HttpClient.create()
				.responseTimeout(timeout);

		ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

		return WebClient.builder()
				.baseUrl(baseUrl)
				.clientConnector(connector)
				.build();
	}
}