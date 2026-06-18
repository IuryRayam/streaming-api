package com.github.iuryrayam.streaming.repository;

import com.github.iuryrayam.streaming.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
