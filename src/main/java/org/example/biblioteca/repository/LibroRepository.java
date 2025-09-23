package org.example.biblioteca.repository;

import org.example.biblioteca.entity.Autor;
import org.example.biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository<Libro,Integer> {
    // Buscar por código (único)
    Optional<Libro> findByCodigo(String codigo);

    // Buscar por categoría
    List<Libro> findByAutor(Autor autor);

    // Buscar por ID de categoría
    List<Libro> findByAutorId(Integer autorId);

    // Buscar por nombre (contiene - ignorando mayúsculas/minúsculas)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);


}
