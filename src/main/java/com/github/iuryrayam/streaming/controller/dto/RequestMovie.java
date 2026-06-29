package com.github.iuryrayam.streaming.controller.dto;

import com.github.iuryrayam.streaming.model.enums.GenderMovie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RequestMovie(
        @NotBlank(message = "campo obrigatorio")
        String title,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "não pode ser uma data futura")
        LocalDate releaseDate,
        @NotNull(message = "campo obrigatorio")
        Integer duration,
        @NotNull(message = "campo obrigatorio")
        Integer rating,
        @NotBlank(message = "campo obrigatorio")
        String studio,
        GenderMovie gender,
        BigDecimal price,
        @NotNull(message = "campo obrigatorio")
        UUID idDirector
) {
}
