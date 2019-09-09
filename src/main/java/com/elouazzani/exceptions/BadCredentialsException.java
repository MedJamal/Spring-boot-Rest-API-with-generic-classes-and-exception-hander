package com.elouazzani.exceptions;

import org.springframework.http.HttpStatus;
/*
 * TODO: Rechecks
 */
public class BadCredentialsException extends ApiBaseException {

	public BadCredentialsException(String message) {
		super(message);
	}

	// TODO : Forbidden
	@Override
	public HttpStatus getStatusCode() {
		return HttpStatus.FORBIDDEN;
	}

}
