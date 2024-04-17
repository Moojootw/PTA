package pet.store.controller.error;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	//provides a better described point of potential failure when an exception occurs 
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String,String> handlerForNoSuchElementException(NoSuchElementException ex) {
		
		log.error("Error: {}", ex.toString());
		return Collections.singletonMap("message", ex.toString());
	}
}
