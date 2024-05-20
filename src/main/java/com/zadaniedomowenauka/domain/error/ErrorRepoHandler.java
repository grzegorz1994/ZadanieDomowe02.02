package com.zadaniedomowenauka.domain.error;

import com.zadaniedomowenauka.domain.controller.GitHubController;
import com.zadaniedomowenauka.domain.model.RepoNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = GitHubController.class)
@Log4j2
public class ErrorRepoHandler {
    @ExceptionHandler(RepoNotFoundException.class)
    public ResponseEntity<ErrorRepoResponseDto> errorHandler(RepoNotFoundException exception){
       log.warn("RepoNotFoundException while accessing repo");
       ErrorRepoResponseDto response = new ErrorRepoResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
