package com.zadaniedomowenauka.domain.proxy.dto;

import com.zadaniedomowenauka.domain.model.Repo;

import java.util.List;

public record GetAllRepoResponseDto(List<RepoDto> repos) {
}
