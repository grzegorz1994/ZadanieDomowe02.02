package com.zadaniedomowenauka.domain.proxy.dto;

import java.util.List;

public record AllInfo(String name, Owner owner, List<GitHubBranchResult> branchList) {
}
