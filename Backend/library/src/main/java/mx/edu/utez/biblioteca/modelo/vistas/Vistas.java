package mx.edu.utez.biblioteca.modelo.vistas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "vistas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vistas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numeroVisitas",nullable = false, length = 100)
    private Integer numeroVisitas;
    @Column(name ="fechaCreacion",nullable = false, length = 100)
    private Date fechaCreacion;
    @Column(name="status",nullable = false, columnDefinition = "tinyint default 1")
    ///@JsonIgnore
    private boolean status;
}
