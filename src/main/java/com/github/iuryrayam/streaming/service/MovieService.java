package com.github.iuryrayam.streaming.service;

import com.github.iuryrayam.streaming.model.Movie;
import com.github.iuryrayam.streaming.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    public Movie save(Movie movie){
        return repository.save(movie);
    }

    public Optional<Movie> buscarPorId(UUID id){
        return repository.findById(id);
    }
}
