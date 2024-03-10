package com.zadaniedomowenauka.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadaniedomowenauka.domain.proxy.dto.GitHubBranchResult;
import com.zadaniedomowenauka.domain.proxy.dto.GithubResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Component
@Log4j2
public class ItunesMapper {

    private final ObjectMapper objectMapper;

    public ItunesMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    List<GithubResult> mapJsonToItunesResultList(String json){
        try {
            return objectMapper.readValue(json, new TypeReference<List<GithubResult>>() {

            });
        } catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }

    List<GitHubBranchResult> mapJsonToGitHubBranchResult(String json){
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }
}
