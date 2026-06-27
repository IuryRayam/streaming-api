package com.github.iuryrayam.streaming.validator;

import com.github.iuryrayam.streaming.exception.RegistroDuplicadoException;
import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.repository.DirectorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DirectorValidator {

    private DirectorRepository repository;

    public DirectorValidator(DirectorRepository repository){
        this.repository = repository;
    }

    public void validar(Director director){
        if (existDirectorCadastrado(director)){
            throw new RegistroDuplicadoException("Director já está cadastrado!");
        }
    }

    private boolean existDirectorCadastrado(Director director){
        Optional<Director> directorEncontrado = repository.findByNameAndDateBirthAndNationality(
                director.getName(), director.getDateBirth(), director.getNationality()
        );

        if (director.getId() == null){
            return directorEncontrado.isPresent();
        }

        return !director.getId().equals(directorEncontrado.get().getId()) && directorEncontrado.isPresent();
    }
}
