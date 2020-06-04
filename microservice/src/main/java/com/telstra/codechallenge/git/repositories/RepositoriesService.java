package com.telstra.codechallenge.git.repositories;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.telstra.codechallenge.git.common.GitDataNotfoundException;
import com.telstra.codechallenge.git.common.GitResponse;
import com.telstra.codechallenge.git.common.Search;

@Service
public class RepositoriesService {

	@Value("${repositories.base.url}")
	private String repositoriesBaseUrl;

	private RestTemplate restTemplate;

	public RepositoriesService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Returns an array of Repositories. Taken from
	 * https://developer.github.com/v3/search/#search-repositories.
	 *
	 * @return - a Repository array
	 */
	public GitResponse<GitRepository> getRepositories(Search search) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(repositoriesBaseUrl + "/search/repositories")
				// @formatter:off
				// Add query parameter
				.queryParam("q", search.getQuery())
				.queryParam("sort", search.getSort())
				.queryParam("order", search.getOrder().value())
				.queryParam("since", search.getSince().value())
				.queryParam("page", search.getPage())
				.queryParam("per_page", search.getLimit());
				// @formatter:on
		
		RequestEntity<Void> requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(builder.toUriString()));
		ParameterizedTypeReference<GitResponse<GitRepository>> type = new ParameterizedTypeReference<GitResponse<GitRepository>>() {
		};
		ResponseEntity<GitResponse<GitRepository>> response = restTemplate.exchange(requestEntity, type);

		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		}

		throw new GitDataNotfoundException("Unable to retrive the repositories");

	}
}