package mx.edu.utez.biblioteca.services;


import mx.edu.utez.biblioteca.modelo.usuario.Usuario;
import mx.edu.utez.biblioteca.modelo.usuario.UsuarioRepository;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Usuario>> getAll(){
        return new CustomResponse<List<Usuario>>(
                this.usuarioRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Activos
    @Transactional(readOnly = true)
    public CustomResponse<List<Usuario>> getAllActive(){
        return new CustomResponse<>(
                this.usuarioRepository.findAllByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    //Inactivos
    @Transactional(readOnly = true)
    public CustomResponse<List<Usuario>> getAllInactive(){
        return new CustomResponse<>(
                this.usuarioRepository.findAllByStatus(false),
                false,
                200,
                "Ok"
        );
    }

    ///Buscar por id
    @Transactional(readOnly = true)
    public CustomResponse<Usuario> getOne(Long id){
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return new CustomResponse<>(
                    usuarioOptional.get(),
                    false,
                    200,
                    "ok"
            );
        } else {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "Usuario no encontrado"
            );
        }
    }

    //Crear
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Usuario> insert(Usuario usuario){
        return new CustomResponse<>(
                this.usuarioRepository.save(usuario),
                false,
                201,
                "Usuario creado correctamente"
        );
    }

    //Actualizar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Usuario> update(Usuario usuario){
        if (!this.usuarioRepository.existsById(usuario.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Usuario no encontrado"
            );
        }
        return new CustomResponse<>(
                this.usuarioRepository.saveAndFlush(usuario),
                false,
                200,
                "Usuario actualizado correctamente"
        );
    }

    //Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus (Usuario usuario){
        if (!this.usuarioRepository.existsById(usuario.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Usuario no encontrado"
            );
        }
        return new CustomResponse<>(
                this.usuarioRepository.updateStatusById(
                        usuario.isStatus(), usuario.getId()) == 1,
                false,
                200,
                "Usuario actualizado correctamente"
        );
    }

    //Eliminar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (!this.usuarioRepository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Usuario no encontrado"
            );
        }
        this.usuarioRepository.deleteById(id);
        return new CustomResponse<>(
                null,
                false,
                200,
                "Usuario eliminado correctamente"
        );
    }

}
