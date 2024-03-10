package com.zadaniedomowenauka.domain.error;

import org.springframework.http.HttpStatusCode;

public record ErrorItunesResponse(String message, HttpStatusCode status) {
}
