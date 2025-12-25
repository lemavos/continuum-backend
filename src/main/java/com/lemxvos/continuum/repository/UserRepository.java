package com.lemxvos.continuum.repository;

import com.lemxvos.continuum.entity.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);

}
