package com.github.iuryrayam.streaming.controller;

import com.github.iuryrayam.streaming.controller.dto.ErroResposta;
import com.github.iuryrayam.streaming.controller.dto.RequestMovie;
import com.github.iuryrayam.streaming.controller.dto.ResponseMovie;
import com.github.iuryrayam.streaming.controller.mapper.MovieMapper;
import com.github.iuryrayam.streaming.exception.RegistroDuplicadoException;
import com.github.iuryrayam.streaming.model.Movie;
import com.github.iuryrayam.streaming.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController implements GenericController {

    private final MovieService service;
    private final MovieMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RequestMovie dto){
        try {
            Movie entity = mapper.toEntity(dto);
            service.save(entity);
            URI location = gerarHeaderLocation(entity.getId());
            return ResponseEntity.created(location).build();
        }catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
