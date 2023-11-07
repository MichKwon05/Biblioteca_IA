package mx.edu.utez.biblioteca.controllers;

import jakarta.validation.Valid;
import mx.edu.utez.biblioteca.dtos.VistasDto;
import mx.edu.utez.biblioteca.modelo.vistas.Vistas;
import mx.edu.utez.biblioteca.services.VistasService;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/vistas/")
@CrossOrigin(origins = {"*"})
public class VistasController {

    @Autowired
    private VistasService vistasService;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Vistas>>> getAll(){
        return new ResponseEntity<>(
                this.vistasService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getActive")
    public ResponseEntity<CustomResponse<List<Vistas>>> getAllActive(){
        return new ResponseEntity<>(
                this.vistasService.getAllActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getInactive")
    public ResponseEntity<CustomResponse<List<Vistas>>> getAllInactive(){
        return new ResponseEntity<>(
                this.vistasService.getAllInactive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Vistas>> getById(
            @PathVariable("id")
            Long id){
        return new ResponseEntity<>(
                this.vistasService.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Vistas>> insert(
            @RequestBody @Valid VistasDto vistasDto,
            @Valid BindingResult result)
            throws Exception {
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.vistasService.insert(vistasDto.getVistas()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Vistas>> update(
            @RequestBody VistasDto vistasDto,
            @Valid BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.vistasService.update(vistasDto.getVistas()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> enableDisable(
            @PathVariable Long id,
            @RequestBody VistasDto vistasDto
    ){
        return new ResponseEntity<>(
                this.vistasService.changeStatus(vistasDto.getVistas()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id) {
        CustomResponse<Boolean> response = vistasService.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
