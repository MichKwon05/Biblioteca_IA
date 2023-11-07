package mx.edu.utez.biblioteca.modelo.renta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentaRepository extends JpaRepository<Renta, Long> {

    @Modifying
    @Query(
            value = "UPDATE rentas SET status = :status WHERE id_renta = :id_renta",
            nativeQuery = true
    )
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id_renta") Long id
    );

    List<Renta> findAllByStatus(Boolean status);
    Renta getById(Long id);
}
