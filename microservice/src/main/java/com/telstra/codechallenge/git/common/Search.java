package com.telstra.codechallenge.git.common;

/**
 * It is helper to pass search criteria
 *
 */
public class Search {

	private String query;
	private String sort;
	private Order order;
	private Since since;
	private Integer page;
	private Integer limit;

	/**
	 * Query property is mandatory with out this service will throw Exception.
	 * 
	 * @return
	 */
	public String getQuery() {
		if (query == null) {
			throw new RuntimeException("Query is not present");
		}
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Order getOrder() {
		return order != null ? order : Order.ASC;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Since getSince() {
		return since != null ? since : Since.DAILY;
	}

	public void setSince(Since since) {
		this.since = since;
	}

	public Integer getPage() {
		return page == null || page <= 0 ? 1 : page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit == null || limit <= 0 ? 10 : limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}
