package com.zadaniedomowenauka.domain.service;
import com.zadaniedomowenauka.domain.proxy.GitHubProxy;
import com.zadaniedomowenauka.domain.proxy.dto.AllInfoDto;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubBranchResultDto;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubResultDto;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubListAllResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {

    private final GitHubProxy gitHubProxy;
    private final GitHubMapper gitHubMapper;

    public GitHubService(GitHubProxy gitHubProxy, GitHubMapper gitHubMapper) {
        this.gitHubProxy = gitHubProxy;
        this.gitHubMapper = gitHubMapper;
    }

    public List<GitHubResultDto> fetchAllRepos(String username){
            String json = gitHubProxy.makeGetRequest(username);
            return gitHubMapper.mapJsonToItunesResultList(json)
                    .stream()
                    .filter(gitHubResultDto -> !gitHubResultDto.fork())
                    .toList();


    }

    public List<GitHubBranchResultDto> fetchAllBranches(String owner, String repo){
        String json = gitHubProxy.makeGetBranches(owner, repo);
        return gitHubMapper.mapJsonToGitHubBranchResult(json);
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
