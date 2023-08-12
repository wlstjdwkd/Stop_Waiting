package com.stopwaiting.server.exception.exhandler.advice;

import com.stopwaiting.server.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegarlExHandler(IllegalArgumentException e){
      log.error("[exceptionHandler] ex",e);
      return new ErrorResult("BAD",e.getMessage());
    }
}
