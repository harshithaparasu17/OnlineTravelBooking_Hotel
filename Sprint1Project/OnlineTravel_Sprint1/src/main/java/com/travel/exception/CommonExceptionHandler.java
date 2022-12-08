package com.travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // controller advice is used for global exception handler
public class CommonExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<AllExceptionDTO> abcde(javax.persistence.EntityNotFoundException e) {
		System.out.println(" ------------- inside --- @Controller Advice Not Found Exception ----");

		AllExceptionDTO dto = new AllExceptionDTO();

		dto.setMessage(e.getLocalizedMessage());

		return new ResponseEntity<AllExceptionDTO>(dto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<AllExceptionDTO> abcd(javax.persistence.NoResultException r) {
		System.out.println("-------------------inside @Controller Advice No result Exception");
		AllExceptionDTO dto = new AllExceptionDTO();
		dto.setMessage(r.getLocalizedMessage());
		return new ResponseEntity<AllExceptionDTO>(dto, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler
	public ResponseEntity<AllExceptionDTO> abc(javax.persistence.QueryTimeoutException q) {
		System.out.println("--------------inside @Controller Advice Query time out exception");
		AllExceptionDTO dto = new AllExceptionDTO();
		dto.setMessage(q.getLocalizedMessage());
		return new ResponseEntity<AllExceptionDTO>(dto, HttpStatus.TOO_MANY_REQUESTS);
	}

}
