package com.github.iuryrayam.streaming.repository;

import com.github.iuryrayam.streaming.model.Director;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class DirectorRepositoryTest {

    @Autowired
    DirectorRepository repository;

    @Test
    void salvarTest(){
        Director director = new Director();
        director.setName("Redfield");
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
}
