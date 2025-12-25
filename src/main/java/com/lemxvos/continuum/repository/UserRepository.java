package com.lemxvos.continuum.repository;

import com.lemxvos.continuum.entity.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
    
    boolean existsByEmail(String email);

}