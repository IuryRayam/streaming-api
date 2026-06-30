package com.github.iuryrayam.streaming.controller.mapper;

import com.github.iuryrayam.streaming.controller.dto.DirectorDTO;
import com.github.iuryrayam.streaming.model.Director;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "dateBirth", target = "dateBirth")
    @Mapping(source = "nationality", target = "nationality")
    Director toEntity(DirectorDTO dto);

    DirectorDTO toDTO(Director director);
}
