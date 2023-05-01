package com.exchange.rate.application.exception;

import javax.servlet.http.HttpServletResponse;


public abstract class NotFoundException extends LocalizedException {

    protected NotFoundException(String code, Object[] args, Throwable cause) {
        super(code, args, cause);
    }

    protected NotFoundException(String code) {
        super(code);
    }

    protected NotFoundException(String code, Throwable cause) {
        super(code, cause);
    }

    protected NotFoundException(String code, Object... args) {
        super(code, args);
    }

    @Override
    public int getStatusCode() {
        return HttpServletResponse.SC_NOT_FOUND;
    }

}
