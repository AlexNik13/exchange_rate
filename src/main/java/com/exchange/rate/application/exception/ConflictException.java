package com.exchange.rate.application.exception;


import javax.servlet.http.HttpServletResponse;

public class ConflictException extends LocalizedException {

    protected ConflictException(String code, Object[] args, Throwable cause) {
        super(code, args, cause);
    }

    protected ConflictException(String code) {
        super(code);
    }

    protected ConflictException(String code, Throwable cause) {
        super(code, cause);
    }

    protected ConflictException(String code, Object... args) {
        super(code, args);
    }

    @Override
    public int getStatusCode() {
        return HttpServletResponse.SC_CONFLICT;
    }

}
