package com.github.iuryrayam.streaming.controller;

import com.github.iuryrayam.streaming.controller.dto.DirectorDTO;
import com.github.iuryrayam.streaming.controller.mapper.DirectorMapper;
import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("director")
@RequiredArgsConstructor
public class DirectorController implements GenericController {

    private final DirectorService service;
    private final DirectorMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid DirectorDTO dto){
        var director = mapper.toEntity(dto);
        service.save(director);

        URI location = gerarHeaderLocation(director.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<DirectorDTO> buscarPorId(@PathVariable String id){
        UUID idDirector = UUID.fromString(id);

        return service
                .buscarPorId(idDirector)
                .map(director -> {
                    DirectorDTO dto = mapper.toDTO(director);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        UUID idDirector = UUID.fromString(id);
        Optional<Director> directorOptional = service.buscarPorId(idDirector);

        if (directorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(directorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DirectorDTO>> pesquisa(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality){
        List<Director> pesquisa = service.pesquisaByExample(name, nationality);
        List<DirectorDTO> list = pesquisa
                .stream()
                .map(mapper::toDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody DirectorDTO dto){
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
    }
}
