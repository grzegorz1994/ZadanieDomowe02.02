package com.zadaniedomowenauka.domain.proxy.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateRepoRequestDto(
        @NotNull(message = "owner must not be null")
        @NotEmpty(message = "owner must not is empty")
        String owner,
        @NotNull(message = "name must not be null")
        @NotEmpty(message = "name must not is empty")
        String name) {
}
