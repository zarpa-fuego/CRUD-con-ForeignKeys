package org.example.biblioteca.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.example.biblioteca.dto.LibroDto;
import org.example.biblioteca.entity.Libro;
import org.example.biblioteca.mapper.LibroMapper;
import org.example.biblioteca.repository.AutorRepository;
import org.example.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroMapper libroMapper;

    public LibroDto save(LibroDto libroDto) {
        if (libroDto.getAutorId() != null) {
            if(!autorRepository.existsById(libroDto.getAutorId())) {
                throw new EntityNotFoundException("Autor con ID" + libroDto.getAutorId() + " no encontrada");
            }
        }
        Libro libro = libroMapper.libroDTOToLibro(libroDto);
        Libro libroSaved = libroRepository.save(libro);
        return libroMapper.libroToLibroDTO(libroSaved);
    }

    public List<LibroDto> obtenerTodosLosLibros() {
        List<Libro> libros = libroRepository.findAll();
        return libros.stream().map(libroMapper::libroToLibroDTO).collect(Collectors.toList());
    }

    public LibroDto obtenerLibroPorId(Integer id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.map(libroMapper::libroToLibroDTO).orElseThrow(() -> new EntityNotFoundException("Libro con ID" + id + " no encontrada"));
    }

    public LibroDto obtenerLibroPorCodigo(String codigo) {
        Optional<Libro> libro = libroRepository.findByCodigo(codigo);
        return libro.map(libroMapper::libroToLibroDTO).orElseThrow(() -> new EntityNotFoundException("Libro con ID" + codigo + " no encontrada"));
    }

    public List<LibroDto> obtenerLibrosPorAutor(Integer autorId) {
        List<Libro> libros = libroRepository.findByAutorId(autorId);
        return libros.stream().map(libroMapper::libroToLibroDTO).collect(Collectors.toList());
    }

    public List<LibroDto> obtenerLibrosPorNombre(String titulo) {
        List<Libro> libros = libroRepository.findByTituloContainingIgnoreCase(titulo);
        return libros.stream().map(libroMapper::libroToLibroDTO).collect(Collectors.toList());
    }

    public void eliminarLibro(Integer id) {
        if (!libroRepository.existsById(id)) {
            throw new EntityNotFoundException("Libro no encontrado");
        }
        libroRepository.deleteById(id);
    }
}
