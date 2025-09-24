package org.example.biblioteca.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibroDto {

    private Integer id;

    @NotNull
    @Size(min = 4, max = 20, message = "El nombre de la comida debe ser de 4 a 20 carácteres")
    private String titulo;

    @NotNull(message = "El codigo no puede ser nulo")
    @Size(min = 3, max = 10, message = "3 a 10 carácteres")
    private String codigo;

   // @NotNull(message = "Debería existir el Author")
    private Integer autorId;
    private String autorNombre;
    private String autorCodigo;
}
