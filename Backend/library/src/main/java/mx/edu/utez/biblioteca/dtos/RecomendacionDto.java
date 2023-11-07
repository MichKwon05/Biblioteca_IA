package mx.edu.utez.biblioteca.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.recomendacion.Recomendacion;
import mx.edu.utez.biblioteca.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RecomendacionDto {

    private Long id;
    private String nombre;
    private Date dia_creacion;
    private Boolean status;
    private Usuario usuario;

    public Recomendacion getRecomendacion() {
        return new Recomendacion(
                getId(),
                getNombre(),
                getDia_creacion(),
                getStatus(),
                getUsuario()
        );
    }
}
