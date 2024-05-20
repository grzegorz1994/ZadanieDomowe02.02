package com.zadaniedomowenauka.domain.service;

import com.zadaniedomowenauka.domain.model.Repo;
import com.zadaniedomowenauka.domain.model.RepoNotFoundException;
import com.zadaniedomowenauka.domain.repository.RepoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoRetriever {

    private final RepoRepository repoRepository;

    public RepoRetriever(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public List<Repo> findAll(Pageable pageable){
        return repoRepository.findAll(pageable);
    }

    public void existById(Long id){
        if (!repoRepository.existsById(id)){
            throw new RepoNotFoundException("Repo with id: " + id + " not exist");
        }
    }

    public Repo findRepoById(Long id){
        return repoRepository.findById(id)
                .orElseThrow(() -> new RepoNotFoundException("Repo with id: " + id + " not found"));
    }
}
