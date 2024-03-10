package com.zadaniedomowenauka.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubBranchResultDto;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubResultDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Log4j2
public class GitHubMapper {

    private final ObjectMapper objectMapper;

    public GitHubMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    List<GitHubResultDto> mapJsonToItunesResultList(String json){
        try {
            return objectMapper.readValue(json, new TypeReference<List<GitHubResultDto>>() {

            });
        } catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }

    List<GitHubBranchResultDto> mapJsonToGitHubBranchResult(String json){
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }
}
