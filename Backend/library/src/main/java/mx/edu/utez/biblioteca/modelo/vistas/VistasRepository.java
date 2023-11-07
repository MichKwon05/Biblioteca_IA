package mx.edu.utez.biblioteca.modelo.vistas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VistasRepository extends JpaRepository<Vistas, Long> {
    @Modifying
    @Query(
            value = "UPDATE vistas SET status = :status WHERE id = :id",
            nativeQuery = true
    )
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

    List<Vistas> findByStatus(Boolean status);
    Vistas getById(Long id);
}
