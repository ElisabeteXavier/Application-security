package com.iftm.products.infra.database;

import com.iftm.products.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository< User,Long> {
    Optional<UserDetails> findByLogin(String login);
}
