package com.zadaniedomowenauka.domain.error;

import com.zadaniedomowenauka.domain.controller.GitHubController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(assignableTypes = GitHubController.class)
@Log4j2
public class ErrorExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorItunesResponseDto> errorHandle(UserNotFoundException exception){
        log.warn("user not found");
        ErrorItunesResponseDto response = new ErrorItunesResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(response);
    }
}
