package br.com.acbueno.data.exception;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

  private HttpStatus status;

  private String error;

  private String message;

}
