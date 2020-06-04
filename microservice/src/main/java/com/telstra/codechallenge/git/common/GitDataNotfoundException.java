package com.telstra.codechallenge.git.common;

public class GitDataNotfoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GitDataNotfoundException(String message) {
		super(message);
	}
}
