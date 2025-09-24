package org.example.biblioteca.mapper;

import org.example.biblioteca.dto.LibroDto;
import org.example.biblioteca.entity.Autor;
import org.example.biblioteca.entity.Libro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibroMapper {
    LibroMapper INSTANCE = Mappers.getMapper(LibroMapper.class);

    @Mapping(source = "autor.id", target = "autorId")
    @Mapping(source = "autor.nombre", target = "autorNombre")
    @Mapping(source = "autor.codigo", target = "autorCodigo")
    LibroDto libroToLibroDTO(Libro libro);

    @Mapping(source = "autorId", target = "autor", qualifiedByName = "idToAutor")
    Libro libroDTOToLibro(LibroDto libroDTO);

    @Named("idToAutor")
    default Autor idToAutor(Integer autorId) {
        if (autorId == null) {
            return null;
        }
        // Solo crear referencia con el ID para JPA
        Autor autor = new Autor();
        autor.setId(autorId);
        return autor;
    }
}
