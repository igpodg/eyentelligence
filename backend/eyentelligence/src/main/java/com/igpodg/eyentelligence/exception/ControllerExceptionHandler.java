package com.igpodg.eyentelligence.exception;

import lombok.SneakyThrows;
import org.hibernate.exception.ConstraintViolationException;
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

//import javax.validation.ConstraintViolationException;
import java.util.Collections;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final String contentType = "Content-Type";
    private final String applicationJson = "application/json;charset=utf-8";

    private final static JSONObject SERVER =
            new JSONObject(Collections.singletonMap("error", "ERROR_TYPE_SERVER"));
    private final static JSONObject USER =
            new JSONObject(Collections.singletonMap("error", "ERROR_TYPE_USER"));

    private final static String ERR_INVALID_REQUEST_ARGUMENT =
            "A given request argument is invalid. Please check your input.";
    private final static String ERR_HTTP_METHOD_UNSUPPORTED =
            "The HTTP method used is not supported.";
    private final static String ERR_SELECTOR_INVALID_TYPE =
            "The selector type in the URL is incorrect.";

    @ExceptionHandler(EyenUserException.class)
    protected ResponseEntity<String> handleTypeUser(EyenUserException exception)
            throws JSONException
    {
        JSONObject result = new JSONObject(USER, new String[] {"error"});
        result.put("reason", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(this.contentType, this.applicationJson)
                .body(result.toString());
    }

    @ExceptionHandler(EyenServerException.class)
    protected ResponseEntity<String> handleTypeServer(EyenServerException exception)
            throws JSONException
    {
        JSONObject result = new JSONObject(SERVER, new String[] {"error"});
        result.put("reason", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(this.contentType, this.applicationJson)
                .body(result.toString());
    }

    @SneakyThrows(JSONException.class)
    @Override
    protected ResponseEntity<Object>
        handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                     HttpHeaders headers,
                                     HttpStatus status,
                                     WebRequest request)
    {
        JSONObject result = new JSONObject(USER, new String[] {"error"});
        result.put("reason", ERR_INVALID_REQUEST_ARGUMENT);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(this.contentType, this.applicationJson)
                .body(result.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<String> handleConstraintViolation(ConstraintViolationException exception) {
        System.out.println("constraint violation");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(this.contentType, this.applicationJson)
                .body(SERVER.toString());
    }

    @SneakyThrows(JSONException.class)
    @Override
    protected ResponseEntity<Object>
        handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
                                            HttpHeaders headers,
                                            HttpStatus status,
                                            WebRequest request)
    {
        JSONObject result = new JSONObject(USER, new String[] {"error"});
        result.put("reason", ERR_HTTP_METHOD_UNSUPPORTED);
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(this.contentType, this.applicationJson)
                .body(result.toString());
    }

    @SneakyThrows(JSONException.class)
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exception,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request)
    {
        JSONObject result = new JSONObject(USER, new String[] {"error"});
        result.put("reason", ERR_SELECTOR_INVALID_TYPE);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(this.contentType, this.applicationJson)
                .body(result.toString());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleUnknown(Exception exception) throws JSONException {
        JSONObject result = new JSONObject(SERVER, new String[] {"error"});
        result.put("reason", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(this.contentType, this.applicationJson)
                .body(result.toString());
    }
}
