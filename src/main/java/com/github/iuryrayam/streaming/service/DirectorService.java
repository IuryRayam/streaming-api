package com.github.iuryrayam.streaming.service;

import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void update(Director director){
        if (director.getId() == null){
            throw new IllegalArgumentException("O director tem que está cadastrado para poder ser atualizado.");
        }
        repository.save(director);
    }

    public Optional<Director> buscarPorId(UUID id){
        return repository.findById(id);
    }

    public void delete(Director director){
        repository.delete(director);
    }

    public List<Director> pesquisa(String name, String nationality){
        if (name != null && nationality != null){
            return repository.findByNameAndNationality(name, nationality);
        }

        if (name != null){
            return repository.findByName(name);
        }

        if (nationality != null){
            return repository.findByNationality(nationality);
        }

        return repository.findAll();
    }
}
