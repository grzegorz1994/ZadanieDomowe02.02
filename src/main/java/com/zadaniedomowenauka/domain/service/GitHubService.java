package com.zadaniedomowenauka.domain.service;
import com.zadaniedomowenauka.domain.proxy.ItunesProxy;
import com.zadaniedomowenauka.domain.proxy.dto.AllInfo;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubBranchResult;
import com.zadaniedomowenauka.domain.proxy.dto.GithubResult;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubListAllResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItunesService {

    private final ItunesProxy itunesProxy;
    private final ItunesMapper itunesMapper;

    public ItunesService(ItunesProxy itunesProxy, ItunesMapper itunesMapper) {
        this.itunesProxy = itunesProxy;
        this.itunesMapper = itunesMapper;
    }

    public List<GithubResult> fetchAllRepos(String username){
            String json = itunesProxy.makeGetRequest(username);
            return itunesMapper.mapJsonToItunesResultList(json)
                    .stream()
                    .filter(githubResult -> !githubResult.fork())
                    .toList();


    }

    public List<GitHubBranchResult> fetchAllBranches(String owner, String repo){
        String json = itunesProxy.makeGetBranches(owner, repo);
        return itunesMapper.mapJsonToGitHubBranchResult(json);
    }

    public GitHubListAllResult getAllSong(String username){
        List<AllInfo> allInfos = new ArrayList<>();
        List<GithubResult> itunesResults = fetchAllRepos(username);
        for (GithubResult result : itunesResults){
            List<GitHubBranchResult> branchResults = fetchAllBranches(result.owner().login(), result.name());
            AllInfo allInformation = new AllInfo(result.name(), result.owner(), branchResults);
            allInfos.add(allInformation);
        }
        return new GitHubListAllResult(allInfos);
    }
}
