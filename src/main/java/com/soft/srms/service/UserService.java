package com.soft.srms.service;

import com.soft.srms.model.User;

/**
 * Interface for storing user data in a database.
 */
public interface UserService {
    /**
     * Stores and returns the new user with the default role of ROLE_STUDENT.
     *
     * @param user      new user
     * @return          new user
     */
    User createUserAccount(User user);

    /**
     * Checks if the given username is taken.
     *
     * @param username          username
     * @return                  confirmation if the username is taken
     */
    boolean isUsernameTaken(String username);

    /**
     * Validates the password.
     *
     * @param password      password
     * @return              confirmation if the password is valid
     */
    boolean validatePassword(String password);

    /**
     * Confirms if user's password matches with given password.
     *
     * @param user          user
     * @param password      password
     * @return              confirmation if user's password matches with given password
     */
    boolean confirmPassword(User user, String password);

    /**
     * Changes the user's password.
     *
     * @param user          user
     * @param newPassword   new password
     */
    void changePassword(User user, String newPassword);
}
