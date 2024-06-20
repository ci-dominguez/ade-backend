package com.ci_dominguez.ade_backend.repository;

import com.ci_dominguez.ade_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address
     *
     * @param email The email address of the user to find
     * @return An Optional containing the user if found, or an empty Optional if not found
     */
    User findByEmail(String email);
}
