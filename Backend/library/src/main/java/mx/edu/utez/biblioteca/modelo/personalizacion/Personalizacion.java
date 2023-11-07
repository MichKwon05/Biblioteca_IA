package mx.edu.utez.biblioteca.modelo.personalizacion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personalizacion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Personalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_personalizacion;

    @Column(nullable = false, length = 100)
    private String tema;

    @Column(nullable = false, length = 100)
    private String tamano_letra;

}
