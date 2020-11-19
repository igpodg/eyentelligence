package com.igpodg.eyentelligence.repository;

import com.igpodg.eyentelligence.CrudRefreshRepository;
import com.igpodg.eyentelligence.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRefreshRepository<Team, Integer> {
    @Override
    List<Team> findAll();

    @Override
    Optional<Team> findById(Integer id);

    @Override
    Team save(Team entity);

    @Override
    void refresh(Team entity);
}
