package com.igpodg.eyentelligence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaRefreshRepository<T, ID> extends JpaRepository<T, ID> {
    void refresh(T entity);
}
