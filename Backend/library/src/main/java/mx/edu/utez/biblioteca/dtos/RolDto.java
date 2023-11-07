package mx.edu.utez.biblioteca.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.rol.Rol;
import mx.edu.utez.biblioteca.modelo.usuario.Usuario;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RolDto {

    private Long idRol;
    private String nombreRol;
    private Boolean status;
    private List<Usuario> usuario;

    public Rol getRol(){
        return new  Rol(
                getIdRol(),
                getNombreRol(),
                getStatus(),
                getUsuario()
        );
    }
}
