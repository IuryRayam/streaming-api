package com.github.iuryrayam.streaming.controller.dto;

import com.github.iuryrayam.streaming.model.Director;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DirectorDTO(
        UUID id,
        @NotBlank(message = "campo obrigatorio")
        String name,
        @NotNull(message = "campo obrigatorio")
        LocalDate dateBirth,
        @NotBlank(message = "campo obrigatorio")
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
