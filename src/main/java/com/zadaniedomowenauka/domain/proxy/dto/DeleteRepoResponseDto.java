package com.zadaniedomowenauka.domain.proxy.dto;

import org.springframework.http.HttpStatus;

public record DeleteRepoResponseDto(String message, HttpStatus status) {
}
