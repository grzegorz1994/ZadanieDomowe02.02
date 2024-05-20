package com.zadaniedomowenauka.domain.service;

import com.zadaniedomowenauka.domain.repository.RepoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class RepoDeleter {

    private final RepoRepository repoRepository;
    private final RepoRetriever repoRetriever;

    public RepoDeleter(RepoRepository repoRepository, RepoRetriever repoRetriever) {
        this.repoRepository = repoRepository;
        this.repoRetriever = repoRetriever;
    }

    public void deleteById(Long id){
        repoRetriever.existById(id);
        log.info("deleting repo by id: " + id);
        repoRepository.deleteById(id);
    }
}
