package com.github.iuryrayam.streaming.repository;

import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.model.Movie;
import com.github.iuryrayam.streaming.model.enums.GenderMovie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class DirectorRepositoryTest {

    @Autowired
    DirectorRepository repository;

    @Autowired
    MovieRepository movieRepository;

    @Test
    void saveTest(){
        Director director = new Director();
        director.setName("Grace");
        director.setDateBirth(LocalDate.of(2001, 8, 10));
        director.setNationality("Brasileira");

        var directorSaved = repository.save(director);
        System.out.println("Director saved: " + directorSaved);
    }

    @Test
    void updateTest(){
        UUID id = UUID.fromString("dd320ac1-fa6d-416f-8b0e-9a074817a350");
        Optional<Director> directorOptional = repository.findById(id);

        if (directorOptional.isPresent()){
            Director director = directorOptional.get();
            director.setName("Claire Redfield");
            director.setNationality("Brasileira");
            director.setDateBirth(LocalDate.of(2001, 8, 17));

            Director directorSaved = repository.save(director);
            System.out.println("Director updated: " + directorSaved.getDateBirth());
        }
    }

    @Test
    void findAll(){
        List<Director> listDirector = repository.findAll();
        listDirector.forEach(System.out::println);
    }

    @Test
    void findById(){
        UUID id = UUID.fromString("dd320ac1-fa6d-416f-8b0e-9a074817a350");
        Director director = repository.findById(id).get();
        System.out.println(director);
    }

    @Test
    void deleteById(){
        repository.deleteById(UUID.fromString("c60a923a-6743-42d0-9d51-206a25070867"));
    }

    @Test
    void delete(){
        UUID id = UUID.fromString("67833685-ba95-4bb4-9f87-bc883e37784a");
        Director director = repository.findById(id).get();
        repository.delete(director);
    }

    @Test
    void saveDirectorWithMovies(){
        Director director = new Director();
        director.setName("Ashley");
        director.setDateBirth(LocalDate.of(2005, 9, 17));
        director.setNationality("Brasileira");

        Movie movie = new Movie();
        movie.setTitle("Resident Evil 4");
        movie.setReleaseDate(LocalDate.of(2026, 6, 30));
        movie.setDuration(190);
        movie.setRating(16);
        movie.setStudio("Capcom");
        movie.setGender(GenderMovie.FICTION);
        movie.setPrice(BigDecimal.valueOf(200));
        movie.setDirector(director);

        Movie movie2 = new Movie();
        movie2.setTitle("Resident Evil 9");
        movie2.setReleaseDate(LocalDate.of(2026, 6, 30));
        movie2.setDuration(190);
        movie2.setRating(16);
        movie2.setStudio("Capcom");
        movie2.setGender(GenderMovie.FICTION);
        movie2.setPrice(BigDecimal.valueOf(200));
        movie2.setDirector(director);

        director.setMovies(new ArrayList<>());
        director.getMovies().add(movie);
        director.getMovies().add(movie2);

        repository.save(director);

        movieRepository.saveAll(director.getMovies());
    }

    @Test
    void findMoviesDirector(){
        UUID id = UUID.fromString("87b406e6-0516-4134-9c1a-f21864f5ebcf");
        Director director = repository.findById(id).orElse(null);

        List<Movie> listMovies = movieRepository.findByDirector(director);
        director.setMovies(listMovies);

        director.getMovies().forEach(System.out::println);
    }
}
