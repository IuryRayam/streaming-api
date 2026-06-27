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
import java.util.List;
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
                .findById(UUID.fromString("a121fc9a-e9ed-4320-97d3-3ad0e4358fb5"))
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

    @Test
    void findByTitleTest(){
        List<Movie> spiderMan = repository.findByTitle("Spider man");
        spiderMan.forEach(System.out::println);
    }

    @Test
    void findByStudioTest(){
        List<Movie> listStudio = repository.findByStudio("Capcom");
        listStudio.forEach(System.out::println);
    }

    @Test
    void findByTitleAndPriceTest(){
        List<Movie> list = repository.findByTitleAndPrice("Spider man", BigDecimal.valueOf(200));
        list.forEach(System.out::println);
    }

    @Test
    void findByStudioOrPriceTest(){
        List<Movie> list = repository.findByStudioOrPrice("Marvel", BigDecimal.valueOf(100));
        list.forEach(System.out::println);
    }

    @Test
    void listarFilmeOrdenadoPorTituloEPreco(){
        List<Movie> list = repository.listarFilmesOrdenadoPorTituloEPreco();
        list.forEach(System.out::println);
    }

    @Test
    void listarDiretoresDosFilmes(){
        List<Movie> list = repository.listarDiretoresDosFilmes();
        list.forEach(System.out::println);
    }

    @Test
    void listarDiferentesNomes(){
        List<String> list = repository.listarNomesDiferentesFilmes();
        list.forEach(System.out::println);
    }

    @Test
    void listarGenerosDiretoresBrasileiros(){
        List<Movie> list = repository.listarGenerosDiretoresBrazileiros();
        list.forEach(System.out::println);
    }

    @Test
    void findByGenderTest(){
        List<Movie> list = repository.findByGender(GenderMovie.FICTION, "releaseDate");
        list.forEach(System.out::println);
    }

    @Test
    void findByGenderPositionalParameters(){
        List<Movie> list = repository.findByGenderPositionalParameters(GenderMovie.FICTION, "releaseDate");
        list.forEach(System.out::println);
    }

    @Test
    void deleteByGender(){
        repository.deleteByGender(GenderMovie.FANTASY);
    }

    @Test
    void updateReleaseDate(){
        repository.updateReleaseDate(LocalDate.of(2026, 6, 17));
    }
}
