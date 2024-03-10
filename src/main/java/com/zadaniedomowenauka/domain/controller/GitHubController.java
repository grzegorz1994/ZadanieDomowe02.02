package com.zadaniedomowenauka.domain.controller;
import com.zadaniedomowenauka.domain.proxy.dto.AllInfo;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubListAllResult;
import com.zadaniedomowenauka.domain.service.ItunesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class ItunesController {
    private final ItunesService itunesService;

    public ItunesController(ItunesService itunesService) {
        this.itunesService = itunesService;
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<GitHubListAllResult> getAllInfo(@PathVariable String username){
        List<AllInfo> allInfoList = itunesService.getAllSong(username).allInfoList();
        GitHubListAllResult response = new GitHubListAllResult(allInfoList);
        return ResponseEntity.ok(response);
    }
}
