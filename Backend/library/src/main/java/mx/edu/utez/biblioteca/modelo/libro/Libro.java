package mx.edu.utez.biblioteca.modelo.libro;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.biblioteca.modelo.renta.Renta;

import java.util.List;

@Entity
@Table(name = "libros")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo",nullable = false, length = 100)
    private String titulo;
    @Column(name = "autor",nullable = false, length = 100)
    private String autor;
    @Column(name = "genero",nullable = false, length = 100)
    private String genero;
    @Column(name = "anio",nullable = false)
    private String anio;
    @Column(name = "descripcion",nullable = false, length = 200)
    private String descripcion;
    @Column(name = "status",nullable = false, columnDefinition = "tinyint default 1")
    //@JsonIgnore
    private boolean status;
    @Column(name = "url_reporte")
    private String urlReporte;

    @OneToMany(mappedBy = "libro")
    private List<Renta> renta;

}
