package com.igpodg.eyentelligence.repository;

import com.igpodg.eyentelligence.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    void deleteAll();

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    User save(User entity);
}
