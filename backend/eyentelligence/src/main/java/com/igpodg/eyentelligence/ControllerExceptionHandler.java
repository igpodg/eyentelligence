package com.igpodg.eyentelligence;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EyenNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String handleNotFound(EyenNotFoundException exception) {
        return "{\"error\":\"STATUS_NOTFOUND\"}";
    }

    @ExceptionHandler(EyenBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleBadRequest(EyenBadRequestException exception) {
        return "{\"error\":\"STATUS_BADREQUEST\"}";
    }
}
