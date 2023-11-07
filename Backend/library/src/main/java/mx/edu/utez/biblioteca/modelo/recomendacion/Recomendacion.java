package mx.edu.utez.biblioteca.modelo.recomendacion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.usuario.Usuario;

import java.util.Date;

@Entity
@Table(name = "recomendaciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false, length = 100)
    private String nombre;

    @Column(name = "dia_creacion",nullable = false, length = 150)
    private Date dia_creacion;

    @Column(name = "status", nullable = false,  columnDefinition = "tinyint default 1")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

}
