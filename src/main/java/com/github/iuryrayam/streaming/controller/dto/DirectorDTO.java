package com.github.iuryrayam.streaming.controller.dto;

import com.github.iuryrayam.streaming.model.Director;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record DirectorDTO(
        UUID id,
        @NotBlank(message = "campo obrigatorio")
        @Size(min = 2, max = 150, message = "campo fora do tamanho padrão")
        String name,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dateBirth,
        @NotBlank(message = "campo obrigatorio")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
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
