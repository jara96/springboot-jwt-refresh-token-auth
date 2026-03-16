package com.jara.authapi.token.repository;

import com.jara.authapi.token.model.RefreshToken;
import com.jara.authapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);

}