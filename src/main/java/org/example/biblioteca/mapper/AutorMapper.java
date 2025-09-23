package org.example.biblioteca.mapper;

import org.example.biblioteca.dto.AutorDto;
import org.example.biblioteca.entity.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LibroMapper.class})
public interface AutorMapper {
    AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);

    @Mapping(source = "libros", target = "libros")
    AutorDto autorToAutorDto(Autor autor);

    @Mapping(source = "libros", target = "libros")
    @Mapping(target = "id", ignore = true)
    Autor autorDtoToAutor(AutorDto autorDto);

}
