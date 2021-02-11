package com.igpodg.eyentelligence.repository;

import com.igpodg.eyentelligence.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    void deleteAll();

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User entity);
}
