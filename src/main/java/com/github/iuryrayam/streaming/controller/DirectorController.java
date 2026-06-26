package com.github.iuryrayam.streaming.controller;

import com.github.iuryrayam.streaming.controller.dto.DirectorDTO;
import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.service.DirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("director")
public class DirectorController {

    private final DirectorService service;

    public DirectorController(DirectorService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody DirectorDTO dto){
        var director = dto.toEntity();
        service.save(director);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(director.getId())
                .toUri();

        return ResponseEntity.created(location).build();
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
    public ResponseEntity<Void> delete(@PathVariable String id){
        UUID idDirector = UUID.fromString(id);
        Optional<Director> directorOptional = service.buscarPorId(idDirector);

        if (directorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.delete(directorOptional.get());
        return ResponseEntity.noContent().build();
    }
}
