package mx.edu.utez.biblioteca.modelo.rol;

import mx.edu.utez.biblioteca.modelo.recomendacion.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    @Modifying
    @Query(
            value = "UPDATE roles SET status = :status WHERE id_rol = :id_rol",
            nativeQuery = true
    )
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id_rol") Long id
    );

    List<Rol> findAllByStatus(Boolean status);
    Rol getById(Long id);
}
