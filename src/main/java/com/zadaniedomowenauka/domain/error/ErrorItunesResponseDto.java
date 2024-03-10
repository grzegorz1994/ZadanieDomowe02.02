package com.zadaniedomowenauka.domain.error;

import org.springframework.http.HttpStatusCode;

public record ErrorItunesResponseDto(String message, HttpStatusCode status) {
}
