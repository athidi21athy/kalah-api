package com.athidi21athy.kalahapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="Invalid move")
public class InvalidMoveException extends RuntimeException {
}
