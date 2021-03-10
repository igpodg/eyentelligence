package com.igpodg.eyentelligence.repository;

import com.igpodg.eyentelligence.model.Dashboard;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardRepository extends JpaRefreshRepository<Dashboard, Integer> {
    void delete(Dashboard entity);

    List<Dashboard> findAll();

    Optional<Dashboard> findById(Integer id);

    Dashboard save(Dashboard entity);

    void refresh(Dashboard entity);
}
