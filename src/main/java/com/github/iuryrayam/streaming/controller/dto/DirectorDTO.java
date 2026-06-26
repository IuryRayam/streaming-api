package com.github.iuryrayam.streaming.controller.dto;

import com.github.iuryrayam.streaming.model.Director;

import java.time.LocalDate;
import java.util.UUID;

public record DirectorDTO(
        UUID id,
        String name,
        LocalDate dateBirth,
        String nationality
) {

    public Director toEntity(){
        Director director = new Director();
        director.setName(name);
        director.setDateBirth(dateBirth);
        director.setNationality(nationality);
        return director;
    }
}
