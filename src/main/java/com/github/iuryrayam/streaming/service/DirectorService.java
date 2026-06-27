package com.github.iuryrayam.streaming.service;

import com.github.iuryrayam.streaming.exception.OperacaoInvalidaException;
import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.repository.DirectorRepository;
import com.github.iuryrayam.streaming.repository.MovieRepository;
import com.github.iuryrayam.streaming.validator.DirectorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorService {

    private final DirectorRepository repository;
    private final DirectorValidator validator;
    private final MovieRepository movieRepository;

    public DirectorService(
            DirectorRepository repository,
            DirectorValidator validator,
            MovieRepository movieRepository){
        this.repository = repository;
        this.validator = validator;
        this.movieRepository = movieRepository;
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
        if (moviesLigadoDirector(director)){
            throw new OperacaoInvalidaException("Não pode deletar um diretor que está vinculado a um filme!");
        }
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

    public boolean moviesLigadoDirector(Director director){
        return movieRepository.existsByDirector(director);
    }
}
