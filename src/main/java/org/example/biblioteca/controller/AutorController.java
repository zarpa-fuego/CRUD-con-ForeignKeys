package org.example.biblioteca.controller;
import jakarta.validation.Valid;
import org.example.biblioteca.dto.AutorDto;
import org.example.biblioteca.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/autors")
public class AutorController {
    public AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }
    // Crear nueva venta
    @PostMapping
    public ResponseEntity<AutorDto> crearAutor(@Valid @RequestBody AutorDto autorDto) {
        System.out.println("Creando Autor");
        System.out.println("Autor: " + autorDto.getNombre());
        System.out.println("Autor: " + autorDto.getLibros());
        AutorDto autorCreado = autorService.save(autorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorCreado);
    }

    // Obtener todas las ventas
    @GetMapping
    public List<AutorDto> ObtenerAutores() {
        return autorService.obtenerTodosLosAutores();
    }

    // Obtener venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<AutorDto> obtenerAutor(@PathVariable Integer id) {
        AutorDto autor = autorService.findById(id);
        return ResponseEntity.ok(autor);
    }

    // Eliminar venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Integer id) {
        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
