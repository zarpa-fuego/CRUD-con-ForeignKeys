package org.example.biblioteca.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.biblioteca.dto.AutorDto;
import org.example.biblioteca.dto.LibroDto;
import org.example.biblioteca.entity.Autor;
import org.example.biblioteca.entity.Libro;
import org.example.biblioteca.mapper.AutorMapper;
import org.example.biblioteca.repository.AutorRepository;
import org.example.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;
    private final LibroRepository libroRepository;

    public AutorServiceImpl(AutorRepository autorRepository, AutorMapper autorMapper, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
        this.libroRepository = libroRepository;
    }

    @Override
    public List<AutorDto> findAll() {
        return List.of();
    }

    public AutorDto findById(Integer id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(autorMapper::autorToAutorDto).orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
    }
    public AutorDto save(AutorDto autorDto) {
        if (autorDto.getLibros() == null || autorDto.getLibros().isEmpty()) {
            throw new IllegalArgumentException("El autor debe tener al menos un libro");
        }
        Autor autor = new Autor();
        Libro libro = new Libro();
        autor.setNombre(autorDto.getNombre());
        autor.addLibro(libro);
        Autor autorGuardar = autorRepository.save(autor);
        return autorMapper.autorToAutorDto(autorGuardar);
    }

    public List<AutorDto> obtenerTodosLosAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autores.stream().map(autorMapper::autorToAutorDto).collect(Collectors.toList());
    }


    public void deleteById(Integer id) {
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
        autorRepository.deleteById(id);
    }

    @Override
    public AutorDto update(AutorDto autorDto) {
        return null;
    }

}
