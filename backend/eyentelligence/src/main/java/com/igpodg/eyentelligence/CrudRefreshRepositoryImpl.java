package com.igpodg.eyentelligence;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class CrudRefreshRepositoryImpl<T, ID>
        extends SimpleJpaRepository<T, ID> implements CrudRefreshRepository<T, ID>
{
    private final EntityManager entityManager;

    public CrudRefreshRepositoryImpl(JpaEntityInformation<T, ID> entityInformation,
                                     EntityManager entityManager)
    {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void refresh(T entity) {
        this.entityManager.refresh(entity);
    }
}
