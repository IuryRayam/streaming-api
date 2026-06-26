package com.github.iuryrayam.streaming.repository;

import com.github.iuryrayam.streaming.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {

    List<Director> findByName(String name);
    List<Director> findByNationality(String nationality);
    List<Director> findByNameAndNationality(String name, String nationality);
}
