/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.lemxvos.continuum.dto;

import java.time.LocalDate;

/**
 *
 * @author lemxvos
 */
public record MetaRequestDTO(
    String titulo,
    LocalDate dataInicio,
    LocalDate dataFim
) {}