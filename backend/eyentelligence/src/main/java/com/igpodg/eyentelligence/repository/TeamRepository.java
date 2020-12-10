package com.igpodg.eyentelligence.repository;

import com.igpodg.eyentelligence.JpaRefreshRepository;
import com.igpodg.eyentelligence.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRefreshRepository<Team, Long> {
    @Override
    void delete(Team entity);

    @Override
    List<Team> findAll();

    @Override
    Optional<Team> findById(Long id);

    @Override
    Team save(Team entity);

    @Override
    void refresh(Team entity);
}
