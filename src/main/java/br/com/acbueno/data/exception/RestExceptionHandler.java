package br.com.acbueno.data.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
    ApiError apiError =
        new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server error", ex.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,
      WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

}
