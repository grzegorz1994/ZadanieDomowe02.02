package com.zadaniedomowenauka.domain.proxy.dto;

import java.util.List;

public record AllInfoDto(String name, Owner owner, List<GitHubBranchResultDto> branchList) {
}
