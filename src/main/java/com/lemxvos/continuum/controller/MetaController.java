package com.lemxvos.continuum.controller;

import org.springframework.web.bind.annotation.*;
import com.lemxvos.continuum.service.MetaService;
import com.lemxvos.continuum.dto.MetaCreateDTO;
import com.lemxvos.continuum.entity.Meta;

@RestController
@RequestMapping("/metas")
public class MetaController {

    private final MetaService metaService;

    public MetaController(MetaService metaService) {
        this.metaService = metaService;
    }

    @PostMapping("/user/{userId}")
    public Meta criarMeta(
            @PathVariable Long userId,
            @RequestBody MetaCreateDTO dto
    ) {
        return metaService.criarMeta(userId, dto);
    }
}
