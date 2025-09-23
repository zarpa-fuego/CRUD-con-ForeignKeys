package org.example.biblioteca.controller;

import jakarta.validation.Valid;
import org.example.biblioteca.dto.LibroDto;
import org.example.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    private LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<LibroDto> crearLibro(@Valid @RequestBody LibroDto libroDto) {
        LibroDto libroGuardado = libroService.save(libroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroDto> actualizarLibro(@PathVariable Integer ido, @Valid @RequestBody LibroDto libroDto) {
        libroDto.setId(ido);
        LibroDto libroActualizado = libroService.save(libroDto);
        return ResponseEntity.ok(libroActualizado);
    }

    @GetMapping
    public List<LibroDto> obtenerLibros() {
        return libroService.obtenerTodosLosLibros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDto> obtenerLibro(@PathVariable Integer id) {
        LibroDto libro = libroService.obtenerLibroPorId(id);
        return ResponseEntity.ok(libro);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<LibroDto> obtenerLibroCodigo(@PathVariable String codigo) {
        LibroDto libro = libroService.obtenerLibroPorCodigo(codigo);
        return ResponseEntity.ok(libro);
    }
    @GetMapping("/autor/{autorId}")
    public List<LibroDto> obtenerLibroPorAutor(@PathVariable Integer autorId) {
        return libroService.obtenerLibrosPorAutor(autorId);
    }

    @GetMapping("/buscar")
    public List<LibroDto> buscarLibros(@RequestParam String titulo) {
        return libroService.obtenerLibrosPorNombre (titulo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarLibro(@PathVariable Integer id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }



}
