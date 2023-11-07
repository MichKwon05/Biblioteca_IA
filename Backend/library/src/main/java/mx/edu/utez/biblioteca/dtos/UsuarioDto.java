package mx.edu.utez.biblioteca.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.personalizacion.Personalizacion;
import mx.edu.utez.biblioteca.modelo.recomendacion.Recomendacion;
import mx.edu.utez.biblioteca.modelo.renta.Renta;
import mx.edu.utez.biblioteca.modelo.rol.Rol;
import mx.edu.utez.biblioteca.modelo.usuario.Usuario;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioDto {

    private Long id;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String correo;
    private String contrasena;
    private Integer telefono;
    private Boolean status;

    private Rol rol;
    //private Personalizacion personalizacion;
    private List<Recomendacion> recomendacions;
    private List<Renta> rentas;

    public Usuario getUsuario(){
        return new Usuario(
                getId(),
                getNombre(),
                getApePaterno(),
                getApeMaterno(),
                getCorreo(),
                getContrasena(),
                getTelefono(),
                getStatus(),
                getRol(),
                getRecomendacions(),
                //getPersonalizacion()
                getRentas()
        );
    }
}
