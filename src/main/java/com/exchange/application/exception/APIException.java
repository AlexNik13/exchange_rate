package com.exchange.application.exception;


import javax.servlet.http.HttpServletResponse;

public class APIException extends LocalizedException {

  protected APIException(String code, Object[] args, Throwable cause) {
    super(code, args, cause);
  }

  protected APIException(String code) {
    super(code);
  }

  protected APIException(String code, Throwable cause) {
    super(code, cause);
  }

  protected APIException(String code, Object... args) {
    super(code, args);
  }

  @Override
  public int getStatusCode() {
    return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
  }

}
