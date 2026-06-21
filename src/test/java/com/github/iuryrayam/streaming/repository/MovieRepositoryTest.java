package com.github.iuryrayam.streaming.repository;

import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.model.Movie;
import com.github.iuryrayam.streaming.model.enums.GenderMovie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository repository;

    @Autowired
    DirectorRepository directorRepository;

    @Test
    void saveTest(){
        Movie movie = new Movie();
        movie.setTitle("Spider man");
        movie.setReleaseDate(LocalDate.of(2026, 6, 30));
        movie.setDuration(190);
        movie.setRating(16);
        movie.setStudio("Marvel");
        movie.setGender(GenderMovie.FICTION);
        movie.setPrice(BigDecimal.valueOf(200));

        Director director = directorRepository
                .findById(UUID.fromString("dd320ac1-fa6d-416f-8b0e-9a074817a350"))
                .orElse(null);

        movie.setDirector(director);
        repository.save(movie);
    }

    @Test
    void updateTest(){
        UUID id = UUID.fromString("70135980-d26b-4e71-9614-d3e31cdbeaf1");
        Director director = directorRepository.findById(id).orElse(null);

        UUID idMovie = UUID.fromString("95148cdd-a828-4473-953a-ec648a11467e");
        Movie movie = repository.findById(idMovie).orElse(null);

        movie.setDirector(director);

        repository.save(movie);
    }

    @Test
    void delete(){
        UUID id = UUID.fromString("95148cdd-a828-4473-953a-ec648a11467e");
        repository.deleteById(id);
    }

    @Test
    @Transient
    void buscarMovieTest(){
        UUID id = UUID.fromString("91d7feba-5f81-4ba0-852f-dad1ba6f30a0");
        Movie movie = repository.findById(id).orElse(null);

        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Direcotr: " + movie.getDirector().getName());
    }
}
