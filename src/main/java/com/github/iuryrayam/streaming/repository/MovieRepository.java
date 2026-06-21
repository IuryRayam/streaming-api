package com.github.iuryrayam.streaming.repository;

import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    // Query Method
    List<Movie> findByDirector(Director director);

    List<Movie> findByTitle(String title);

    List<Movie> findByStudio(String studio);

    List<Movie> findByTitleAndPrice(String title, BigDecimal price);

    List<Movie> findByStudioOrPrice(String studio, BigDecimal price);
}
