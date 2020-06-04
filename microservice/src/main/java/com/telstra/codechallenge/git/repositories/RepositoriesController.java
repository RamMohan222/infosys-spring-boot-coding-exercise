package com.telstra.codechallenge.git.repositories;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.git.common.Order;
import com.telstra.codechallenge.git.common.Search;
import com.telstra.codechallenge.git.common.Since;

@RestController
public class RepositoriesController {

	private RepositoriesService repositoriesService;

	public RepositoriesController(RepositoriesService repositoriesService) {
		this.repositoriesService = repositoriesService;
	}

	@RequestMapping(path = "/repositories", method = RequestMethod.GET)
	public List<GitRepository> repositories(@RequestParam(defaultValue = "10") Integer limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("Limit should not be a negative");
		}

		Search search = new Search();
		search.setQuery("stars");
		search.setLimit(limit);
		search.setSort("stars");
		search.setOrder(Order.DESC);
		search.setSince(Since.WEEKLY);
		/*
		 * result validated against a web page
		 * https://github.com/search?o=desc&q=stars&s=stars&type=Repositories
		 */
		return repositoriesService.getRepositories(search).getItems();
	}
}
