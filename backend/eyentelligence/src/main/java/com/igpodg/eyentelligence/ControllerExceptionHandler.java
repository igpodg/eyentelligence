package com.igpodg.eyentelligence;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final JSONObject notFound;
    private final JSONObject badRequest;
    private final JSONObject unknown;

    public ControllerExceptionHandler() throws JSONException {
        this.notFound = new JSONObject();
        this.notFound.put("error", "STATUS_NOTFOUND");
        this.badRequest = new JSONObject();
        this.badRequest.put("error", "STATUS_BADREQUEST");
        this.unknown = new JSONObject();
        this.unknown.put("error", "STATUS_UNKNOWN");
    }

    @ExceptionHandler(EyenNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String handleNotFound(EyenNotFoundException exception) {
        return this.notFound.toString();
    }

    @ExceptionHandler(EyenBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleBadRequest(EyenBadRequestException exception) {
        return this.badRequest.toString();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleUnknown(Exception exception) {
        return this.unknown.toString();
    }
}
