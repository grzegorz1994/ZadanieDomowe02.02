package com.zadaniedomowenauka.domain.controller;
import com.zadaniedomowenauka.domain.model.Repo;
import com.zadaniedomowenauka.domain.proxy.dto.*;
import com.zadaniedomowenauka.domain.service.*;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; 

@RestController
@Log4j2
public class GitHubController {
    private final GitHubService gitHubService;
    private final RepoAdder repoAdder;
    private final RepoRetriever repoRetriever;
    private final RepoDeleter repoDeleter;
    private final RepoUpdater repoUpdater;

    public GitHubController(GitHubService gitHubService, RepoAdder repoAdder, RepoRetriever repoRetriever,
                            RepoDeleter repoDeleter, RepoUpdater repoUpdater) {
        this.gitHubService = gitHubService;
        this.repoAdder = repoAdder;
        this.repoRetriever = repoRetriever;
        this.repoDeleter = repoDeleter;
        this.repoUpdater = repoUpdater;
    }

    @GetMapping(value = "/users/{username}", produces = "application/json")
    public ResponseEntity<GitHubListAllResultDto> getAllInfo(@PathVariable String username){
        List<AllInfoDto> allInfoDtoList = gitHubService.getAllSong(username).allInfoDtoList();
        GitHubListAllResultDto response = new GitHubListAllResultDto(allInfoDtoList);
        for (AllInfoDto i : allInfoDtoList){
            RepoDto repoDto = new RepoDto(i.name(), i.owner().login());
            Repo repo = RepoMapper.mapFromRepoDtoToRepo(repoDto);
            Repo saved = repoAdder.addRepo(repo);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/all")
    public ResponseEntity<GetAllRepoResponseDto> getAllRepo(@PageableDefault(page = 0, size = 5)Pageable pageable){
        List<Repo> allRepos = repoRetriever.findAll(pageable);
        GetAllRepoResponseDto response = RepoMapper.mapFromRepoToGetAllRepoResponseDto(allRepos);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    public ResponseEntity<CreateRepoResponseDto> postRepo(@RequestBody @Valid CreateRepoRequestDto request){
        Repo repo = RepoMapper.mapFromCreateRepoRequestDtoToRepo(request);
        Repo repoSaved = repoAdder.addRepo(repo);
        CreateRepoResponseDto response = RepoMapper.mapFromRepoToCreateRepoResponseDto(repoSaved);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<DeleteRepoResponseDto> deleteById(@PathVariable Long id){
        repoDeleter.deleteById(id);
        DeleteRepoResponseDto response = RepoMapper.mapFromSongToDeleteRepoResponseDto(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<GetRepoResponseDto> getRepoById(@PathVariable Long id){
        Repo repo = repoRetriever.findRepoById(id);
        GetRepoResponseDto response = RepoMapper.mapFromRepoToGetRepoResponseDto(repo);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UpdateRepoResponseDto> updateRepoById(@PathVariable Long id, @RequestBody UpdateRepoRequestDto request){
        Repo newRepo = RepoMapper.mapFromUpdateRepoRequestDtoToRepo(request);
        repoUpdater.updateById(id, newRepo);
        UpdateRepoResponseDto response = RepoMapper.mapFromRepoToUpdateRepoResponseDto(newRepo);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UpdatePartiallyRepoResponseDto> updatePartiallyRepoById(@PathVariable Long id,
                                                                                  @RequestBody UpdatePartiallyRepoRequestDto requestDto){
        Repo updateRepo = RepoMapper.mapFromUpdatePartiallyRepoRequestDtoToRepo(requestDto);
        Repo newUpdateRepo = repoUpdater.updateRepoPartiallyById(id, updateRepo);
        UpdatePartiallyRepoResponseDto response = RepoMapper.mapFromRepoToUpdatePartiallyRepoResponseDto(newUpdateRepo);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/users/{username}", consumes = "application/json", produces = "application/xml")
    public ResponseEntity<GitHubListAllResultDto> getAllInfoResult(@PathVariable String username){
        List<AllInfoDto> allInfoDtoList = gitHubService.getAllSong(username).allInfoDtoList();
        GitHubListAllResultDto response = new GitHubListAllResultDto(allInfoDtoList);
        return ResponseEntity.ok(response);
    }
}
