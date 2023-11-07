package mx.edu.utez.biblioteca.services;

import mx.edu.utez.biblioteca.modelo.renta.Renta;
import mx.edu.utez.biblioteca.modelo.renta.RentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional

public class RentaService {

    @Autowired
    private RentaRepository rentaRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Renta>> getAll(){
        return new CustomResponse<List<Renta>>(
                this.rentaRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Activos
    @Transactional(readOnly = true)
    public CustomResponse<List<Renta>> getAllActive(){
        return new CustomResponse<>(
                this.rentaRepository.findAllByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    //Inactivos
    @Transactional(readOnly = true)
    public CustomResponse<List<Renta>> getAllInactive(){
        return new CustomResponse<>(
                this.rentaRepository.findAllByStatus(false),
                false,
                200,
                "Ok"
        );
    }

    ///Buscar por id
    @Transactional(readOnly = true)
    public CustomResponse<Renta> getOne(Long id){
        Optional<Renta> optional = this.rentaRepository.findById(id);
        if (optional.isPresent()) {
            return new CustomResponse<>(
                    optional.get(),
                    false,
                    200,
                    "ok"
            );
        } else {
            return new CustomResponse<>(
                    null,
                    true,
                    400 ,
                    "Renta no encontrado"
            );
        }
    }

    //Insertar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Renta> insert(Renta renta){
        return new CustomResponse<>(
                this.rentaRepository.save(renta),
                false,
                200,
                "Renta guardado correctamente"
        );
    }

    ///Actualizar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Renta> update(Renta renta){
        if (!this.rentaRepository.existsById(renta.getIdRenta())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Renta no encontrada"
            );
        }
        return new CustomResponse<>(
                this.rentaRepository.saveAndFlush(renta),
                false,
                200,
                "Renta actualizada correctamente"
        );
    }

    ///Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Renta renta) {
        if (!this.rentaRepository.existsById(renta.getIdRenta())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Renta no encontrada"
            );
        }
        return new CustomResponse<>(
                this.rentaRepository.updateStatusById(
                        renta.isStatus(),
                        renta.getIdRenta()
                ) == 1,
                false,
                200,
                "Estado actualizado correctamente"
        );
    }

    ///Eliminar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id) {
        if (!this.rentaRepository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Renta no encontrada"
            );
        }
        this.rentaRepository.deleteById(id);
        return new CustomResponse<>(
                null,
                false,
                200,
                "Renta eliminado correctamente"
        );
    }
}
