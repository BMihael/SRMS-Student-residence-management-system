package com.soft.srms.repository;

import com.soft.srms.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface for more specific CRUD operations on a user.
 */
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Returns a user with given username.
     *
     * @param username      username
     * @return              user with matching username
     */
    User findByUsername(String username);

    /**
     * Checks if a user exists with given username.
     *
     * @param username      username
     * @return              confirmation if a user exists with given username
     */
    boolean existsByUsername(String username);
}
