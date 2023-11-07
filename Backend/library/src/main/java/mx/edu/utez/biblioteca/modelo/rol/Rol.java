package mx.edu.utez.biblioteca.modelo.rol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.usuario.Usuario;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    @Column(name = "nombreRol" ,nullable = false, length = 100)
    private String nombreRol;
    @Column(name = "status",nullable = false, columnDefinition = "tinyint default 1")
    ///@JsonIgnore
    private boolean status;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuario;

}
