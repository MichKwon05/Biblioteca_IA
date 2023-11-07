package mx.edu.utez.biblioteca.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import mx.edu.utez.biblioteca.modelo.libro.Libro;
import mx.edu.utez.biblioteca.modelo.libro.LibroRepository;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Libro>> getAll(){
        return new CustomResponse<List<Libro>>(
                this.libroRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Activos
    @Transactional(readOnly = true)
    public CustomResponse<List<Libro>> getAllActive(){
        return new CustomResponse<>(
                this.libroRepository.findAllByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    //Inactivos
    @Transactional(readOnly = true)
    public CustomResponse<List<Libro>> getAllInactive(){
        return new CustomResponse<>(
                this.libroRepository.findAllByStatus(false),
                false,
                200,
                "Ok"
        );
    }

    ///Buscar por id
    @Transactional(readOnly = true)
    public CustomResponse<Libro> getOne(Long id){
        Optional<Libro> libroOptional = this.libroRepository.findById(id);
        if (libroOptional.isPresent()) {
            return new CustomResponse<>(
                    libroOptional.get(),
                    false,
                    200,
                    "ok"
            );
        } else {
            return new CustomResponse<>(
                    null,
                    true,
                    400 ,
                    "Libro no encontrado"
            );
        }
    }

    //Insertar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Libro> insert(Libro libro){
        return new CustomResponse<>(
                this.libroRepository.save(libro),
                false,
                200,
                "Libro guardado correctamente"
        );
    }

    ///Actualizar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Libro> update(Libro libro){
        if (!this.libroRepository.existsById(libro.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Libro no encontrado"
            );
        }
        return new CustomResponse<>(
                this.libroRepository.saveAndFlush(libro),
                false,
                200,
                "Libro actualizado correctamente"
        );
    }

    ///Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Libro libro) {
        if (!this.libroRepository.existsById(libro.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Libro no encontrado"
            );
        }
        return new CustomResponse<>(
                this.libroRepository.updateStatusById(
                        libro.isStatus(),
                        libro.getId()
                ) == 1,
                false,
                200,
                "Estado actualizado correctamente"
        );
    }

    ///Eliminar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id) {
        if (!this.libroRepository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Libro no encontrado"
            );
        }
        this.libroRepository.deleteById(id);
        return new CustomResponse<>(
                null,
                false,
                200,
                "Libro eliminado correctamente"
        );
    }

}
