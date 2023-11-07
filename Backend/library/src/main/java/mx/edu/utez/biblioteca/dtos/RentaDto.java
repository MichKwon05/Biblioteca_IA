package mx.edu.utez.biblioteca.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.libro.Libro;
import mx.edu.utez.biblioteca.modelo.renta.Renta;
import mx.edu.utez.biblioteca.modelo.usuario.Usuario;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RentaDto {

    private Long idRenta;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Boolean status;

    private Usuario usuario;
    private Libro libro;

    public Renta getRenta(){
        return new Renta(
                getIdRenta(),
                getFecha_inicio(),
                getFecha_fin(),
                getStatus(),
                getUsuario(),
                getLibro()
        );
    }
}
