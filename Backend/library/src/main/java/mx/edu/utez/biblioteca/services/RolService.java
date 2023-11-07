package mx.edu.utez.biblioteca.services;

import mx.edu.utez.biblioteca.modelo.rol.Rol;
import mx.edu.utez.biblioteca.modelo.rol.RolRepository;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Rol>> getAll(){
        return new CustomResponse<List<Rol>>(
                this.rolRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Activos
    @Transactional(readOnly = true)
    public CustomResponse<List<Rol>> getAllActive(){
        return new CustomResponse<>(
                this.rolRepository.findAllByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    //Inactivos
    @Transactional(readOnly = true)
    public CustomResponse<List<Rol>> getAllInactive(){
        return new CustomResponse<>(
                this.rolRepository.findAllByStatus(false),
                false,
                200,
                "Ok"
        );
    }

    ///Buscar por id
    @Transactional(readOnly = true)
    public CustomResponse<Rol> getOne(Long id){
        Optional<Rol> rolOptional = this.rolRepository.findById(id);
        if (rolOptional.isPresent()) {
            return new CustomResponse<>(
                    rolOptional.get(),
                    false,
                    200,
                    "ok"
            );
        } else {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Rol no encontrado"
            );
        }
    }

    ///Insertar
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Rol> insert(Rol rol){
        return new CustomResponse<>(
                this.rolRepository.save(rol),
                false,
                200,
                "Rol creado correctamente"
        );
    }

    ///Actualizar
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Rol> update(Rol rol){
        if (!this.rolRepository.existsById(rol.getIdRol())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Rol no encontrado"
            );
        }
        return new CustomResponse<>(
                this.rolRepository.saveAndFlush(rol),
                false,
                200,
                "Rol actualizado correctamente"
        );
    }

    ///Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Rol rol){
        if (!this.rolRepository.existsById(rol.getIdRol())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Rol no encontrado"
            );
        }
        return new CustomResponse<>(
                this.rolRepository.updateStatusById(
                        rol.isStatus(),
                        rol.getIdRol()
                ) == 1,
                false,
                200,
                "Rol actualizado correctamente"
        );
    }

    ///Eliminar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (!this.rolRepository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Rol no encontrado"
            );
        }
        this.rolRepository.deleteById(id);
        return new CustomResponse<>(
                null,
                false,
                200,
                "Rol eliminado correctamente"
        );
    }

}
