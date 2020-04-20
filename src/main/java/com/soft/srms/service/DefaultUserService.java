package com.soft.srms.service;

import com.soft.srms.model.Role;
import com.soft.srms.model.User;
import com.soft.srms.model.UserRole;
import com.soft.srms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service that implements interface for storing user data in a database.
 */
@Service
public class DefaultUserService implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new instance of the class DefaultUserService with the provided user repository and password encoder.
     *
     * @param userRepository        user repository
     * @param passwordEncoder       password encoder
     */
    public DefaultUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Stores and returns the new user with the default role of ROLE_STUDENT.
     *
     * @param user      new user
     * @return          new user
     */
    @Override
    public User createUserAccount(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.getRoles().add(new Role(user, UserRole.ROLE_STUDENT));
        return userRepository.save(user);
    }

    /**
     * Checks if the given username is taken.
     *
     * @param username          username
     * @return                  confirmation if the username is taken
     */
    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Validates the password.
     *
     * @param password      password
     * @return              confirmation if the password is valid
     */
    @Override
    public boolean validatePassword(String password) {
        return password.length() >= 3;
    }

    /**
     * Confirms if user's password matches with given password.
     *
     * @param user          user
     * @param password      password
     * @return              confirmation if user's password matches with given password
     */
    @Override
    public boolean confirmPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    /**
     * Changes the user's password.
     *
     * @param user          user
     * @param newPassword   new password
     */
    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
