package org.example.biblioteca.service;

import org.example.biblioteca.dto.AutorDto;

import java.util.List;

public interface AutorService {
    public List<AutorDto> findAll();
    public AutorDto findById(Integer id);
    public AutorDto save(AutorDto autorDto);
    public void deleteById(Integer id);
    public AutorDto update(AutorDto autorDto);
    List<AutorDto> obtenerTodosLosAutores();
}
