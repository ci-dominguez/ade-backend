package com.ci_dominguez.ade_backend.service;

import com.ci_dominguez.ade_backend.model.User;
import com.ci_dominguez.ade_backend.model.enums.UserRole;
import com.ci_dominguez.ade_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for UserService
     *
     * @param userRepository Repository for User entity operations
     * @param passwordEncoder Encoder for password hashing
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /////////////////////Service Methods/////////////////////

    /**
     * Creates a new user with the given details
     *
     * @param firstName User's first name
     * @param lastName User's last name
     * @param email User's email address
     * @param password User's password (will be encoded)
     * @param phoneNumber User's phone number
     * @param role User's role
     * @return The created User entity
     */
    public User createUser(String firstName, String lastName, String email, String password, String phoneNumber, UserRole role){
        User user = new User(firstName, lastName, email, password, phoneNumber, role);
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their email address
     *
     * @param email The email address to search for
     * @return The User entity if found, null otherwise
     */
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * Authenticates a user based on email and password
     *
     * @param email The email address of the user
     * @param password The password to verify
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticateUser(String email, String password){
        Optional<User> user = Optional.ofNullable(getUserByEmail(email));
        return user.map(u -> passwordEncoder.matches(password, u.getPassword())).orElse(false);
    }
}
