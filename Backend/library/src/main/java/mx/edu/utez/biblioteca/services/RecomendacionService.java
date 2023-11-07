package mx.edu.utez.biblioteca.services;

import mx.edu.utez.biblioteca.modelo.recomendacion.Recomendacion;
import mx.edu.utez.biblioteca.modelo.recomendacion.RecomendacionRepository;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository recoRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Recomendacion>> getAll(){
        return new CustomResponse<List<Recomendacion>>(
                this.recoRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Activos
    @Transactional(readOnly = true)
    public CustomResponse<List<Recomendacion>> getAllActive(){
        return new CustomResponse<>(
                this.recoRepository.findAllByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    //Inactivos
    @Transactional(readOnly = true)
    public CustomResponse<List<Recomendacion>> getAllInactive(){
        return new CustomResponse<>(
                this.recoRepository.findAllByStatus(false),
                false,
                200,
                "Ok"
        );
    }

    ///Buscar por id
    @Transactional(readOnly = true)
    public CustomResponse<Recomendacion> getOne(Long id){
        Optional<Recomendacion> recoOptional = this.recoRepository.findById(id);
        if (recoOptional.isPresent()){
            return new CustomResponse<>(
                    recoOptional.get(),
                    false,
                    200,
                    "Ok"
            );
        }else {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Recomendación no encontrada"
            );
        }
    }

    ///Insertar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Recomendacion> insert(Recomendacion reco){
        return new CustomResponse<>(
                this.recoRepository.save(reco),
                false,
                200,
                "Recomendación guardada correctamente"
        );
    }

    ///Actualizar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Recomendacion> update(Recomendacion reco){
        if (!this.recoRepository.existsById(reco.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Recomendación no encontrada"
            );
        }
        return new CustomResponse<>(
                this.recoRepository.saveAndFlush(reco),
                false,
                200,
                "Recomendación actualizada correctamente"
        );
    }

    //Cambiar Status
   @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Recomendacion reco) {
        if (!this.recoRepository.existsById(reco.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Recomendación no encontrada"
            );
        }
        return new CustomResponse<>(
                this.recoRepository.updateStatusById(
                        reco.isStatus(),
                        reco.getId()
                ) == 1,
                false,
                200,
                "Estado actualizado correctamente"
        );
    }

    ///Eliminar
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (!this.recoRepository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Recomendación no encontrada"
            );
        }
        this.recoRepository.deleteById(id);
        return new CustomResponse<>(
                true,
                false,
                200,
                "Recomendación eliminada correctamente"
        );
    }

}
