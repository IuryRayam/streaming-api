package com.github.iuryrayam.streaming.repository;

import com.github.iuryrayam.streaming.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
