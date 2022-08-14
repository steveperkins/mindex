package com.mindex.challenge.exception;

/**
 * Thrown when an entity can't be found
 *
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}

}
