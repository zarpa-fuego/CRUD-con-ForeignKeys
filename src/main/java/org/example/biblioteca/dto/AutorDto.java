package org.example.biblioteca.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AutorDto {

    private Integer id;

    @NotNull
    @Size(min = 4, max = 20, message = "El nombre de la comida debe ser de 4 a 20 carácteres")
    private String nombre;

    @NotNull(message = "El codigo no puede ser nulo")
    @Size(min = 3, max = 10, message = "3 a 10 carácteres")
    private String codigo;


    @Valid
    private List<LibroDto> libros = new ArrayList<>();

}
