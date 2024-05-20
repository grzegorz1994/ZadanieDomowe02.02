package com.zadaniedomowenauka.domain.service;

import com.zadaniedomowenauka.domain.model.Repo;
import com.zadaniedomowenauka.domain.repository.RepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RepoAdder {

    private final RepoRepository repository;

    public RepoAdder(RepoRepository repository) {
        this.repository = repository;
    }

    public Repo addRepo(Repo repo){
        log.info("adder new repo: " + repo);
        return repository.save(repo);
    }
}
