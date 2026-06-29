package com.github.iuryrayam.streaming.service;

import com.github.iuryrayam.streaming.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
}
