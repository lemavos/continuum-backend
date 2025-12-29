/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lemxvos.continuum.controller;

import com.lemxvos.continuum.dto.GoalDayDTO;
import com.lemxvos.continuum.dto.MetaRequestDTO;
import com.lemxvos.continuum.dto.MetaResponseDTO;
import com.lemxvos.continuum.entity.Meta;
import com.lemxvos.continuum.repository.MetaRepository;
import com.lemxvos.continuum.service.MetaService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lemxvos
 */
@RestController
@RequestMapping("/metas")
public class MetaController {

    private final MetaService metaService;
    private final MetaRepository metaRepository;

    public MetaController(MetaService metaService, MetaRepository metaRepository) {
        this.metaService = metaService;
        this.metaRepository = metaRepository;
    }

    @PostMapping
    public ResponseEntity<MetaResponseDTO> criar(@RequestBody MetaRequestDTO dto) {
        Meta meta = metaService.criarMeta(dto);
        return ResponseEntity.ok(toDTO(meta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaResponseDTO> buscar(@PathVariable Long id) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));
        return ResponseEntity.ok(toDTO(meta));
    }

    @PatchMapping("/{metaId}/dias/{data}")
    public ResponseEntity<Void> atualizarDia(
            @PathVariable Long metaId,
            @PathVariable LocalDate data,
            @RequestBody Map<String, Boolean> body
    ) {
        metaService.atualizarDia(metaId, data, body.get("concluido"));
        return ResponseEntity.noContent().build();
    }

    private MetaResponseDTO toDTO(Meta meta) {
        List<GoalDayDTO> dias = meta.getDias().stream()
                .map(d -> new GoalDayDTO(d.getData(), d.isConcluido()))
                .toList();

        return new MetaResponseDTO(meta.getId(), meta.getTitulo(), dias);
    }
}

