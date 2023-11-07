package mx.edu.utez.biblioteca.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.vistas.Vistas;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VistasDto {

    private Long id;
    private Integer numeroVisitas;
    private Date fechaCreacion;
    private Boolean status;

    public Vistas getVistas() {
        return new Vistas(
                getId(),
                getNumeroVisitas(),
                getFechaCreacion(),
                getStatus()
        );
    }
}
