package mx.edu.utez.biblioteca.modelo.usuario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.personalizacion.Personalizacion;
import mx.edu.utez.biblioteca.modelo.recomendacion.Recomendacion;
import mx.edu.utez.biblioteca.modelo.renta.Renta;
import mx.edu.utez.biblioteca.modelo.rol.Rol;

import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(name = "apePaterno", nullable = false, length = 200)
    private String apePaterno;
    @Column(name = "apeMaterno", length = 200) //No todos tienen dos apellidos
    private String apeMaterno;
    @Column(name = "correo",nullable = false, length = 100)
    private String correo;
    @Column(name = "contrasena",nullable = false, length = 200)
    private String contrasena;
    @Column(name = "telefono",nullable = false, length = 10)
    private Integer telefono;
    @Column(name = "status",nullable = false, columnDefinition = "tinyint default 1")
    //@JsonIgnore
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    @JsonBackReference
    private Rol rol;
    /*@OneToOne
    @JoinColumn(name = "id_personalizacion")
    @JsonIgnore
    private Personalizacion personalizacion;*/

    @OneToMany(mappedBy = "usuario")
    private List<Recomendacion> reco;

    @OneToMany(mappedBy = "usuario")
    private List<Renta> rentas;


}
