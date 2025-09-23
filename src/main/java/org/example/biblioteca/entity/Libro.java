package org.example.biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 4, max = 20, message = "El nombre de la comida debe ser de 4 a 20 carácteres")
    @Column(nullable = false, unique = true)
    private String titulo;

    @NotNull(message = "El codigo no puede ser nulo")
    @Size(min = 3, max = 10, message = "3 a 10 carácteres")
    @Column(nullable = false, unique = true)
    private String codigo;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false,
            foreignKey = @ForeignKey (name = "FK_libro_autor"))
    @NotNull(message = "El autor debería existir")
    private Autor autor;

}
