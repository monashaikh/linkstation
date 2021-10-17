package com.ncs.linkstation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class LinkStationExceptions {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequestException(
            ResponseStatusException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMessageNotReadableException(HttpMessageNotReadableException exception) {
        String causeMessage = getMessageFromException(exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(causeMessage);
    }

    private String getMessageFromException(HttpMessageNotReadableException exception) {
        Throwable cause = exception.getCause();
        String causeMessage = exception.getMessage();
        if (null != cause) {
            causeMessage = cause.getMessage();
        }
        if (causeMessage.indexOf("at [") > 0) {
            causeMessage = causeMessage.substring(0, causeMessage.indexOf("at ["));
        }
        return causeMessage;
    }
}
