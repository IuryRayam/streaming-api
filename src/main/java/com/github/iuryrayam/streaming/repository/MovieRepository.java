package com.github.iuryrayam.streaming.repository;

import com.github.iuryrayam.streaming.model.Director;
import com.github.iuryrayam.streaming.model.Movie;
import com.github.iuryrayam.streaming.model.enums.GenderMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    // Query Method
    List<Movie> findByDirector(Director director);

    List<Movie> findByTitle(String title);

    List<Movie> findByStudio(String studio);

    List<Movie> findByTitleAndPrice(String title, BigDecimal price);

    List<Movie> findByStudioOrPrice(String studio, BigDecimal price);

    @Query(" select m from Movie as m order by m.title, m.price")
    List<Movie> listarFilmesOrdenadoPorTituloEPreco();

    @Query(" select m from Movie as m join m.director")
    List<Movie> listarDiretoresDosFilmes();

    @Query(" select distinct m.title from Movie as m")
    List<String> listarNomesDiferentesFilmes();

    @Query("""
        select m
        from Movie as m
        join m.director as d
        where d.nationality = 'Brasileira'
    """)
    List<Movie> listarGenerosDiretoresBrazileiros();

    @Query(" select m from Movie as m where m.gender = :gender order by :paramOrder")
    List<Movie> findByGender(@Param("gender") GenderMovie gender, @Param("paramOrder") String nameParam);

    @Query(" select m from Movie as m where m.gender = ?1 order by ?2")
    List<Movie> findByGenderPositionalParameters(GenderMovie gender, String nameParam);

    @Modifying
    @Transactional
    @Query(" delete from Movie where gender = ?1 ")
    void deleteByGender(GenderMovie gender);

    @Modifying
    @Transactional
    @Query(" update Movie set releaseDate = ?1 ")
    void updateReleaseDate(LocalDate newDate);

    boolean existsByDirector(Director director);
}
