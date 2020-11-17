package com.igpodg.eyentelligence.repository;

import com.igpodg.eyentelligence.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
}
