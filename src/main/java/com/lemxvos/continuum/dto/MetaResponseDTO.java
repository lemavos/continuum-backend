/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.lemxvos.continuum.dto;

import java.util.List;

/**
 *
 * @author lemxvos
 */
public record MetaResponseDTO(
    Long id,
    String titulo,
    List<GoalDayDTO> dias
) {}

