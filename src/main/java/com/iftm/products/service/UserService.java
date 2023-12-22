package com.iftm.products.service;

import com.iftm.products.domain.user.User;
import com.iftm.products.infra.database.UserRepository;
import com.iftm.products.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest createUserRequest) {

        var user = userRepository.findByLogin(createUserRequest.getLogin());

        if (user.isPresent()) {
            return ((User) user.get());
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(createUserRequest.getPassword());
            return userRepository.save(
                    new User(
                            createUserRequest.getLogin(),
                            encryptedPassword
                    )
            );
        }

    }
}
