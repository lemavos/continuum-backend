/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lemxvos.continuum.service;

import com.lemxvos.continuum.dto.MetaRequestDTO;
import com.lemxvos.continuum.entity.GoalDay;
import com.lemxvos.continuum.entity.Meta;
import com.lemxvos.continuum.repository.GoalDayRepository;
import com.lemxvos.continuum.repository.MetaRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 *
 * @author lemxvos
 */
@Service
public class MetaService {

    private final MetaRepository metaRepository;
    private final GoalDayRepository goalDayRepository;

    public MetaService(MetaRepository metaRepository, GoalDayRepository goalDayRepository) {
        this.metaRepository = metaRepository;
        this.goalDayRepository = goalDayRepository;
    }

    public Meta criarMeta(MetaRequestDTO dto) {
        Meta meta = new Meta();
        meta.setTitulo(dto.titulo());
        meta.setDataInicio(dto.dataInicio());
        meta.setDataFim(dto.dataFim());

        LocalDate atual = dto.dataInicio();
        while (!atual.isAfter(dto.dataFim())) {
            GoalDay dia = new GoalDay();
            dia.setData(atual);
            dia.setConcluido(false);
            dia.setMeta(meta);
            meta.getDias().add(dia);
            atual = atual.plusDays(1);
        }

        return metaRepository.save(meta);
    }

    public void atualizarDia(Long metaId, LocalDate data, boolean concluido) {
        GoalDay dia = goalDayRepository
                .findByMetaIdAndData(metaId, data)
                .orElseThrow(() -> new RuntimeException("Dia não encontrado"));

        dia.setConcluido(concluido);
        goalDayRepository.save(dia);
    }
}
