package com.github.iuryrayam.streaming.service;

import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.repository.DirectorRepository;
import com.github.iuryrayam.streaming.validator.DirectorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorService {

    private final DirectorRepository repository;
    private final DirectorValidator validator;

    public DirectorService(DirectorRepository repository, DirectorValidator validator){
        this.repository = repository;
        this.validator = validator;
    }

    public void save(Director director){
        validator.validar(director);
        repository.save(director);
    }

    public void update(Director director){
        if (director.getId() == null){
            throw new IllegalArgumentException("O director tem que está cadastrado para poder ser atualizado.");
        }
        validator.validar(director);
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
