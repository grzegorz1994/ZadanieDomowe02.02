package com.zadaniedomowenauka.domain.controller;

import com.zadaniedomowenauka.domain.model.Repo;
import com.zadaniedomowenauka.domain.proxy.dto.*;
import org.springframework.http.HttpStatus;

import java.util.List;

public class RepoMapper {

    public static GetAllRepoResponseDto mapFromRepoToGetAllRepoResponseDto(List<Repo> repos){
        List<RepoDto> repoDtos = repos.stream()
                .map(RepoMapper::mapFromRepoToRepoDto)
                .toList();
        return new GetAllRepoResponseDto(repoDtos);
    }

    public static RepoDto mapFromRepoToRepoDto(Repo repo){
        return new RepoDto(repo.getOwner(), repo.getName());
    }

    public static Repo mapFromRepoDtoToRepo(RepoDto repoDto){
        return new Repo(repoDto.name(), repoDto.owner());
    }

    public static Repo mapFromCreateRepoRequestDtoToRepo(CreateRepoRequestDto requestDto){
        return new Repo(requestDto.owner(), requestDto.name());
    }

    public static CreateRepoResponseDto mapFromRepoToCreateRepoResponseDto(Repo repo){
        RepoDto repoDto = RepoMapper.mapFromRepoToRepoDto(repo);
        return new CreateRepoResponseDto(repoDto);
    }

    public static DeleteRepoResponseDto mapFromSongToDeleteRepoResponseDto(Long id){
        return new DeleteRepoResponseDto("You deleted repo with id: " + id, HttpStatus.OK);
    }

    public static GetRepoResponseDto mapFromRepoToGetRepoResponseDto(Repo repo){
        RepoDto response = RepoMapper.mapFromRepoToRepoDto(repo);
        return new GetRepoResponseDto(response);
    }

    public static Repo mapFromUpdateRepoRequestDtoToRepo(UpdateRepoRequestDto requestDto){
        return new Repo(requestDto.owner(), requestDto.name());
    }

    public static UpdateRepoResponseDto mapFromRepoToUpdateRepoResponseDto(Repo repo){
        return new UpdateRepoResponseDto(repo.getOwner(), repo.getName());
    }

    public static Repo mapFromUpdatePartiallyRepoRequestDtoToRepo(UpdatePartiallyRepoRequestDto requestDto){
        return new Repo(requestDto.owner(), requestDto.name());
    }

    public static UpdatePartiallyRepoResponseDto mapFromRepoToUpdatePartiallyRepoResponseDto(Repo repo){
        RepoDto response = RepoMapper.mapFromRepoToRepoDto(repo);
        return new UpdatePartiallyRepoResponseDto(response);
    }
}
