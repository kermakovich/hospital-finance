package solvd.laba.ermakovich.hf.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import solvd.laba.ermakovich.hf.domain.exception.ResourceDoesNotExistException;
import solvd.laba.ermakovich.hf.web.dto.ErrorDto;

/**
 * @author Ermakovich Kseniya
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleResourceDoesNotExistException(ResourceDoesNotExistException ex) {
        return new ErrorDto(ex.getMessage());
    }
}