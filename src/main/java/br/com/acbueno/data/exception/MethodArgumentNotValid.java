package br.com.acbueno.data.exception;

public class MethodArgumentNotValid extends RuntimeException {

  private static final long serialVersionUID = 2452688289408866236L;

  public MethodArgumentNotValid(String message) {
    super(message);
  }

  public MethodArgumentNotValid(String message, Throwable throwable) {
    super(message, throwable);
  }

}
