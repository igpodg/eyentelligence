package com.igpodg.eyentelligence.repository;

import com.igpodg.eyentelligence.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
    @Override
    List<Team> findAll();

    @Override
    Optional<Team> findById(Integer id);
}
