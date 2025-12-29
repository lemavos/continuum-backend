/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lemxvos.continuum.repository;

import com.lemxvos.continuum.entity.GoalDay;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lemxvos
 */
public interface GoalDayRepository extends JpaRepository<GoalDay, Long> {
    Optional<GoalDay> findByMetaIdAndData(Long metaId, LocalDate data);
}

