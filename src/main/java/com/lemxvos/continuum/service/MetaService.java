package com.lemxvos.continuum.service;

import org.springframework.stereotype.Service;
import com.lemxvos.continuum.entity.*;
import com.lemxvos.continuum.repository.*;
import com.lemxvos.continuum.dto.MetaCreateDTO;

@Service
public class MetaService {

    private final MetaRepository metaRepository;
    private final UserRepository userRepository;

    public MetaService(MetaRepository metaRepository, UserRepository userRepository) {
        this.metaRepository = metaRepository;
        this.userRepository = userRepository;
    }

    public Meta criarMeta(Long userId, MetaCreateDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User não encontrado"));

        Meta meta = new Meta();
        meta.setNome(dto.nome);
        meta.setDataInicio(dto.dataInicio);
        meta.setMetadataJson(dto.metadataJson);
        meta.setProgressoJson("{}");
        meta.setUser(user);

        return metaRepository.save(meta);
    }
}
