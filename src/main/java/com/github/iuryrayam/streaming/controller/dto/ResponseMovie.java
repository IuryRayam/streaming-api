package com.github.iuryrayam.streaming.controller.dto;

import com.github.iuryrayam.streaming.model.enums.GenderMovie;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResponseMovie(
        UUID id,
        String title,
        LocalDate releaseDate,
        Integer duration,
        Integer rating,
        String studio,
        GenderMovie gender,
        BigDecimal price,
        DirectorDTO director
) {
}
