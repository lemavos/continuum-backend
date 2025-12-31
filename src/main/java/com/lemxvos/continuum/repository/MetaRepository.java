package com.lemxvos.continuum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lemxvos.continuum.entity.Meta;
import java.util.List;

public interface MetaRepository extends JpaRepository<Meta, Long> {
    List<Meta> findByUserId(Long userId);
}
