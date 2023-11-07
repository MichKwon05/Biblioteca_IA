package mx.edu.utez.biblioteca.modelo.renta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.libro.Libro;
import mx.edu.utez.biblioteca.modelo.usuario.Usuario;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "rentas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Renta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRenta;

    @Column(name = "fecha_inicio",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_inicio;

    @Column(name = "fecha_fin",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_fin;

    @Column(name = "status",nullable = false, columnDefinition = "tinyint default 1")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    @JsonIgnore
    private Libro libro;


}
