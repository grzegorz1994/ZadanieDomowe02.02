package com.zadaniedomowenauka.domain.service;

import com.zadaniedomowenauka.domain.model.Repo;
import com.zadaniedomowenauka.domain.repository.RepoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RepoUpdater {

    private final RepoRepository repoRepository;
    private final RepoRetriever repoRetriever;

    public RepoUpdater(RepoRepository repoRepository, RepoRetriever repoRetriever) {
        this.repoRepository = repoRepository;
        this.repoRetriever = repoRetriever;
    }

    public void updateById(Long id, Repo newRepo){
        repoRetriever.existById(id);
        repoRepository.updateById(id, newRepo);
    }

    public Repo updateRepoPartiallyById(Long id, Repo repoFromRequest){
        Repo repoFromDatabase = repoRetriever.findRepoById(id);
        Repo.RepoBuilder builder = Repo.builder();
        if (repoFromRequest.getOwner() != null){
            builder.owner(repoFromRequest.getOwner());
        }else{
            builder.owner(repoFromDatabase.getOwner());
        }
        if (repoFromRequest.getName() != null){
            builder.name(repoFromRequest.getName());
        }else{
            builder.name(repoFromDatabase.getName());
        }
        Repo toSaveNewRepo = builder.build();
        updateById(id, toSaveNewRepo);
        return toSaveNewRepo;
    }
}
