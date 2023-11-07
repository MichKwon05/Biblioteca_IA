package mx.edu.utez.biblioteca.controllers;

import jakarta.validation.Valid;
import mx.edu.utez.biblioteca.dtos.RecomendacionDto;
import mx.edu.utez.biblioteca.modelo.recomendacion.Recomendacion;
import mx.edu.utez.biblioteca.services.RecomendacionService;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recomendacion/")
@CrossOrigin(origins = {"*"})
public class RecomendacionController {

    @Autowired
    private RecomendacionService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Recomendacion>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getActive")
    public ResponseEntity<CustomResponse<List<Recomendacion>>> getAllActive(){
        return new ResponseEntity<>(
                this.service.getAllActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getInactive")
    public ResponseEntity<CustomResponse<List<Recomendacion>>> getAllInactive(){
        return new ResponseEntity<>(
                this.service.getAllInactive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Recomendacion>> getById(
            @PathVariable("id")
            Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Recomendacion>> insert(@RequestBody @Valid RecomendacionDto recoDto,
                                                        @Valid BindingResult result)
            throws Exception {
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(recoDto.getRecomendacion()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Recomendacion>> update(
            @RequestBody RecomendacionDto recoDto,
            @Valid BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(recoDto.getRecomendacion()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> enableDisable(
            @PathVariable Long id,
            @RequestBody RecomendacionDto recoDto
    ){
        return new ResponseEntity<>(
                this.service.changeStatus(recoDto.getRecomendacion()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id) {
        CustomResponse<Boolean> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
