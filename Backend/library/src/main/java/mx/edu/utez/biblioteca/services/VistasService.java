package mx.edu.utez.biblioteca.services;

import mx.edu.utez.biblioteca.modelo.vistas.Vistas;
import mx.edu.utez.biblioteca.modelo.vistas.VistasRepository;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Transactional
public class VistasService {

    @Autowired
    private VistasRepository vistasRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Vistas>> getAll() {
        return new CustomResponse<List<Vistas>>(
                this.vistasRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Activos
    @Transactional(readOnly = true)
    public CustomResponse<List<Vistas>> getAllActive() {
        return new CustomResponse<>(
                this.vistasRepository.findByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    //Inactivos
    @Transactional(readOnly = true)
    public CustomResponse<List<Vistas>> getAllInactive() {
        return new CustomResponse<>(
                this.vistasRepository.findByStatus(false),
                false,
                200,
                "Ok"
        );
    }

    ///Buscar por id
    @Transactional(readOnly = true)
    public CustomResponse<Vistas> getOne(Long id) {
        Optional<Vistas> vistasOptional = this.vistasRepository.findById(id);
        if (vistasOptional.isPresent()) {
            return new CustomResponse<>(
                    vistasOptional.get(),
                    false,
                    200,
                    "ok"
            );
        } else {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se encontro el registro"
            );
        }
    }

    //Insertar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Vistas> insert(Vistas vistas){
        return new CustomResponse<>(
                this.vistasRepository.save(vistas),
                false,
                200,
                "Registro guardado correctamente"
        );
    }

    //Actualizar
   @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Vistas> update(Vistas vistas){
        if (!this.vistasRepository.existsById(vistas.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Registro no encontrado"
            );
        }
        return new CustomResponse<>(
                this.vistasRepository.saveAndFlush(vistas),
                false,
                200,
                "Registro actualizado correctamente"
        );
    }

    ///Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Vistas vistas) {
        if (!this.vistasRepository.existsById(vistas.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Registro no encontrado"
            );
        }
        return new CustomResponse<>(
                this.vistasRepository.updateStatusById(
                        vistas.isStatus(),
                        vistas.getId()
                ) == 1,
                false,
                200,
                "Estado actualizado correctamente"
        );
    }

    //Eliminar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id) {
        if (!this.vistasRepository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Registro no encontrado"
            );
        }
        this.vistasRepository.deleteById(id);
        return new CustomResponse<>(
                null,
                false,
                200,
                "Registro eliminado correctamente"
        );
    }
}
