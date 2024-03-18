package com.zadaniedomowenauka.domain.service;
import com.zadaniedomowenauka.domain.proxy.GitHubProxy;
import com.zadaniedomowenauka.domain.proxy.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {

    private final GitHubProxy gitHubProxy;

    public GitHubService(GitHubProxy gitHubProxy) {
        this.gitHubProxy = gitHubProxy;
    }

    public List<GitHubResultDto> fetchAllRepos(String username){
        return gitHubProxy.makeGetRequest(username)
                .stream()
                .filter(gitHubResultDto -> !gitHubResultDto.fork())
                .toList();
    }

    public List<GitHubBranchResultDto> fetchAllBranches(String owner, String repo){
        return gitHubProxy.makeGetBranches(owner, repo);
    }

    public GitHubListAllResultDto getAllSong(String username){
        List<AllInfoDto> allInfos = new ArrayList<>();
        List<GitHubResultDto> itunesResults = fetchAllRepos(username);
        for (GitHubResultDto result : itunesResults){
            List<GitHubBranchResultDto> branchResults = fetchAllBranches(result.owner().login(), result.name());
            AllInfoDto allInformationDto = new AllInfoDto(result.name(), result.owner(), branchResults);
            allInfos.add(allInformationDto);
        }
        return new GitHubListAllResultDto(allInfos);
    }
}
