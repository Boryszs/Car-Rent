package com.Server.controller;

import com.Server.dto.Response.MessageResponse;
import com.Server.exception.WrongDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

/**
 * Exception Handle controller
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-05-14.
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = {ParseException.class , DataAccessException.class , IllegalArgumentException.class, WrongDataException.class})
    public ResponseEntity<MessageResponse> exception(RuntimeException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new MessageResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
