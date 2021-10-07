package com.cosmos.service;

import com.cosmos.error.TransactionError;
import com.cosmos.error.UserAlreadyAvailableException;
import com.cosmos.model.User;
import com.cosmos.pojo.ResponseEntity;
import com.cosmos.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity saveUserRegistration(User user) {
        String response = "";
        log.info("User to be added" + user.getUsername());
        boolean present = checkIfUserAlreadyRegistered(user);
        User encryptedUser = encryptUser(user);
        User savedUser = new User();
        if (!present) {
            encryptedUser.setActive(true);
            try {
                savedUser = userRepository.save(encryptedUser);
                response = "User saved successfully" + savedUser.getUsername();
            } catch (Exception error) {
                throw new TransactionError("Some Error while storing the User");
            }

        } else {
            response = "User already registered , Please Login";
            throw new UserAlreadyAvailableException("User already registered , Please Login");
        }
        log.info(response);
        return new ResponseEntity(HttpStatus.OK,"response");
    }

    private boolean checkIfUserAlreadyRegistered(User user) {
        boolean available = false;
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        log.info("User available: " + optionalUser);
        if (optionalUser.isPresent()) {
            available = true;
        }
        return available;
    }

    private User encryptUser(User user) {
        User encryptedUser = new User();
        encryptedUser.setUsername(user.getUsername());
        encryptedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return encryptedUser;
    }

    public void validateLogin(User user) {
        log.info("Validating for user :" + user.toString());

    }

    public ResponseEntity forgotPassword(Long username, User user) {
        log.info("Updating password for user: " + username);
        boolean present = checkIfUserAlreadyRegistered(user);
        if (present) {
            User encryptedUser = encryptUser(user);
            encryptedUser.setActive(true);
            userRepository.save(encryptedUser);
        }
        return new ResponseEntity(HttpStatus.OK,"Updated Successfully");
    }
}
