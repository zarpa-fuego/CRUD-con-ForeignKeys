package org.example.biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 4, max = 20, message = "El nombre de la comida debe ser de 4 a 20 carácteres")
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotNull(message = "El codigo no puede ser nulo")
    @Size(min = 3, max = 10, message = "3 a 10 carácteres")
    @Column(nullable = false, unique = true)
    private String codigo;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Libro> libros = new ArrayList<>();

    // Métodos de conveniencia para manejar la relación bidireccional
    public void addLibro(Libro libro) {
        libros.add(libro);
        libro.setAutor(this);
    }
}
