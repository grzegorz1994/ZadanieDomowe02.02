package com.zadaniedomowenauka.domain.controller;
import com.zadaniedomowenauka.domain.proxy.dto.AllInfoDto;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubListAllResultDto;
import com.zadaniedomowenauka.domain.service.GitHubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List; 

@RestController
@Log4j2
public class GitHubController {
    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping(value = "/users/{username}", produces = "application/json")
    public ResponseEntity<GitHubListAllResultDto> getAllInfo(@PathVariable String username){
        List<AllInfoDto> allInfoDtoList = gitHubService.getAllSong(username).allInfoDtoList();
        GitHubListAllResultDto response = new GitHubListAllResultDto(allInfoDtoList);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/users/{username}", consumes = "application/json", produces = "application/xml")
    public ResponseEntity<GitHubListAllResultDto> getAllInfoResult(@PathVariable String username){
        List<AllInfoDto> allInfoDtoList = gitHubService.getAllSong(username).allInfoDtoList();
        GitHubListAllResultDto response = new GitHubListAllResultDto(allInfoDtoList);
        return ResponseEntity.ok(response);
    }
}
