package com.igpodg.eyentelligence.repository;

import com.igpodg.eyentelligence.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRefreshRepository<Team, Integer> {
    void delete(Team entity);

    List<Team> findAll();

    Optional<Team> findById(Integer id);

    Team save(Team entity);

    void refresh(Team entity);
}
