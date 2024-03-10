package com.zadaniedomowenauka.domain.proxy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubBranchResultDto(String name, Commit commit) {
}
