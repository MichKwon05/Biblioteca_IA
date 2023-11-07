package mx.edu.utez.biblioteca.controllers;

import jakarta.validation.Valid;
import mx.edu.utez.biblioteca.dtos.RolDto;
import mx.edu.utez.biblioteca.modelo.rol.Rol;
import mx.edu.utez.biblioteca.services.RolService;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol/")
@CrossOrigin(origins = {"*"})
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Rol>>> getAll(){
        return new ResponseEntity<>(
                this.rolService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getActive")
    public ResponseEntity<CustomResponse<List<Rol>>> getAllActive(){
        return new ResponseEntity<>(
                this.rolService.getAllActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getInactive")
    public ResponseEntity<CustomResponse<List<Rol>>> getAllInactive(){
        return new ResponseEntity<>(
                this.rolService.getAllInactive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Rol>> getById(
            @PathVariable("id")
            Long id){
        return new ResponseEntity<>(
                this.rolService.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Rol>> insert(@RequestBody @Valid RolDto rolDto,
                                                        @Valid BindingResult result)
            throws Exception {
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.rolService.insert(rolDto.getRol()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Rol>> update(
            @RequestBody RolDto rolDto,
            @Valid BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.rolService.update(rolDto.getRol()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> enableDisable(
            @PathVariable Long id,
            @RequestBody RolDto rolDto
    ){
        return new ResponseEntity<>(
                this.rolService.changeStatus(rolDto.getRol()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id) {
        CustomResponse<Boolean> response = rolService.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
