package com.github.iuryrayam.streaming.controller;

import com.github.iuryrayam.streaming.controller.dto.DirectorDTO;
import com.github.iuryrayam.streaming.controller.dto.ErroResposta;
import com.github.iuryrayam.streaming.exception.OperacaoInvalidaException;
import com.github.iuryrayam.streaming.exception.RegistroDuplicadoException;
import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService service;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DirectorDTO dto){
        try {
            var director = dto.toEntity();
            service.save(director);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(director.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DirectorDTO> buscarPorId(@PathVariable String id){
        UUID idDirector = UUID.fromString(id);
        Optional<Director> directorOptional = service.buscarPorId(idDirector);

        if (directorOptional.isPresent()){
            Director director = directorOptional.get();
            DirectorDTO dto = new DirectorDTO(
                    director.getId(),
                    director.getName(),
                    director.getDateBirth(),
                    director.getNationality());

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try {
            UUID idDirector = UUID.fromString(id);
            Optional<Director> directorOptional = service.buscarPorId(idDirector);

            if (directorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.delete(directorOptional.get());
            return ResponseEntity.noContent().build();
        } catch (OperacaoInvalidaException e){
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @GetMapping
    public ResponseEntity<List<DirectorDTO>> pesquisa(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality){
        List<Director> pesquisa = service.pesquisaByExample(name, nationality);
        List<DirectorDTO> list = pesquisa.stream().map(director -> new DirectorDTO(
                director.getId(),
                director.getName(),
                director.getDateBirth(),
                director.getNationality()
        )).toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody DirectorDTO dto){
        try {
            UUID idDirector = UUID.fromString(id);
            Optional<Director> directorOptional = service.buscarPorId(idDirector);

            if (directorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var director = directorOptional.get();
            director.setName(dto.name());
            director.setDateBirth(dto.dateBirth());
            director.setNationality(dto.nationality());

            service.update(director);
            return ResponseEntity.noContent().build();
        } catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
