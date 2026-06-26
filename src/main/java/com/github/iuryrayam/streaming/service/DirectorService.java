package com.github.iuryrayam.streaming.service;

import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorService {

    private final DirectorRepository repository;

    public DirectorService(DirectorRepository repository){
        this.repository = repository;
    }

    public void save(Director director){
        repository.save(director);
    }

    public Optional<Director> buscarPorId(UUID id){
        return repository.findById(id);
    }

    public void delete(Director director){
        repository.delete(director);
    }
}
