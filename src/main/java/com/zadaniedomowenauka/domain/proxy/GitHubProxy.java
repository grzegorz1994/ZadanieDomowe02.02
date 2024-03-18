package com.zadaniedomowenauka.domain.proxy;
import com.zadaniedomowenauka.domain.error.UserNotFoundException;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubBranchResultDto;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubResultDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class GitHubProxy {

    private static final String URI_REPOS = "https://api.github.com/users/{username}/repos";
    private static final String URI_BRANCH = "https://api.github.com/repos/{owner}/{repo}/branches";

    private final RestTemplate restTemplate;

    public GitHubProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GitHubResultDto> makeGetRequest(String username){
        try {
            ResponseEntity<GitHubResultDto[]> response = restTemplate.getForEntity(
                    URI_REPOS, GitHubResultDto[].class, username
            );
            GitHubResultDto[] gitHubResultDtos = response.getBody();
            return Arrays.stream(gitHubResultDtos)
                    .collect(Collectors.toList());
        } catch (RestClientResponseException exception){
            throw new UserNotFoundException("username: " + username + " not found");
        } catch (RestClientException exception){
            String message = exception.getMessage();
            log.error(message);
        }
        return null;
    }

    public List<GitHubBranchResultDto> makeGetBranches(String owner, String repo){
        try {
            ResponseEntity<GitHubBranchResultDto[]> response = restTemplate.getForEntity(
                    URI_BRANCH, GitHubBranchResultDto[].class, owner, repo
            );
            GitHubBranchResultDto[] gitHubBranchResultDtos = response.getBody();
            return Arrays.stream(gitHubBranchResultDtos)
                    .collect(Collectors.toList());
        } catch (RestClientResponseException exception){
            String message = exception.getMessage() + exception.getStatusCode().value();
            log.error(message);
        } catch (RestClientException exception){
            String message = exception.getMessage();
            log.error(message);
        }
        return null;
    }
}
