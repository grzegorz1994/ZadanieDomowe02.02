package com.zadaniedomowenauka.domain.model;

public class RepoNotFoundException extends RuntimeException{

    public RepoNotFoundException(String message) {
        super(message);
    }
}
