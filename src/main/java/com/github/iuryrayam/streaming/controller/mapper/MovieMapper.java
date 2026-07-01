package com.github.iuryrayam.streaming.controller.mapper;

import com.github.iuryrayam.streaming.controller.dto.RequestMovie;
import com.github.iuryrayam.streaming.controller.dto.ResponseMovie;
import com.github.iuryrayam.streaming.model.Movie;
import com.github.iuryrayam.streaming.repository.DirectorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = DirectorMapper.class)
public abstract class MovieMapper {

    @Autowired
    DirectorRepository directorRepository;

    @Mapping(target = "director", expression = "java( directorRepository.findById(dto.idDirector()).orElse(null) )")
    public abstract Movie toEntity(RequestMovie dto);

    public abstract ResponseMovie toDTO(Movie movie);
}
