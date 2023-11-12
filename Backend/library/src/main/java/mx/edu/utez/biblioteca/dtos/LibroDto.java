package mx.edu.utez.biblioteca.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.libro.Libro;
import mx.edu.utez.biblioteca.modelo.renta.Renta;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LibroDto {
    private Long id;
    @NotEmpty(message = "Campo Obligatorio")
    private String titulo;
    @NotEmpty(message = "Campo Obligatorio")
    private String autor;
    private String genero;
    private String anio;
    private String descripcion;
    private Boolean status;
    private String urlReporte;
    private List<Renta> rentas;

    public Libro getLibro() {
        return new Libro(
                getId(),
                getTitulo(),
                getAutor(),
                getGenero(),
                getAnio(),
                getDescripcion(),
                getStatus(),
                getUrlReporte(),
                getRentas()
        );
    }
}
