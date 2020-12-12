package com.igpodg.eyentelligence;

import com.igpodg.eyentelligence.exception.EyenBadRequestException;
import com.igpodg.eyentelligence.exception.EyenNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final String contentType = "Content-Type";
    private final String applicationJson = "application/json;charset=utf-8";

    private final JSONObject notFound;
    private final JSONObject badRequest;
    private final JSONObject notSupported;
    private final JSONObject unknown;

    public ControllerExceptionHandler() throws JSONException {
        this.notFound = new JSONObject();
        this.notFound.put("error", "STATUS_NOTFOUND");
        this.badRequest = new JSONObject();
        this.badRequest.put("error", "STATUS_BADREQUEST");
        this.notSupported = new JSONObject();
        this.notSupported.put("error", "STATUS_NOTSUPPORTED");
        this.unknown = new JSONObject();
        this.unknown.put("error", "STATUS_UNKNOWN");
    }

    @ExceptionHandler(EyenNotFoundException.class)
    protected ResponseEntity<String> handleNotFound(EyenNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header(this.contentType, this.applicationJson)
                .body(this.notFound.toString());
    }

    @ExceptionHandler(EyenBadRequestException.class)
    protected ResponseEntity<String> handleBadRequest(EyenBadRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(this.contentType, this.applicationJson)
                .body(this.badRequest.toString());
    }

    @Override
    protected ResponseEntity<Object>
        handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                     HttpHeaders headers,
                                     HttpStatus status,
                                     WebRequest request)
    {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(this.contentType, this.applicationJson)
                .body(this.badRequest.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<String> handleConstraintViolation(ConstraintViolationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(this.contentType, this.applicationJson)
                .body(this.badRequest.toString());
    }

    @Override
    protected ResponseEntity<Object>
        handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
                                            HttpHeaders headers,
                                            HttpStatus status,
                                            WebRequest request)
    {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(this.contentType, this.applicationJson)
                .body(this.notSupported.toString());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exception,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request)
    {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(this.contentType, this.applicationJson)
                .body(this.badRequest.toString());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleUnknown(Exception exception) throws JSONException {
        this.unknown.put("error", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(this.contentType, this.applicationJson)
                .body(this.unknown.toString());
    }
}
