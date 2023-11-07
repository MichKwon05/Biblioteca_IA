package mx.edu.utez.biblioteca.controllers;

import jakarta.validation.Valid;
import mx.edu.utez.biblioteca.dtos.UsuarioDto;
import mx.edu.utez.biblioteca.modelo.usuario.Usuario;
import mx.edu.utez.biblioteca.services.UsuarioService;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuario/")
@CrossOrigin(origins = {"*"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Usuario>>> getAll(){
        return new ResponseEntity<>(
                this.usuarioService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getActive")
    public ResponseEntity<CustomResponse<List<Usuario>>> getAllActive(){
        return new ResponseEntity<>(
                this.usuarioService.getAllActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getInactive")
    public ResponseEntity<CustomResponse<List<Usuario>>> getAllInactive(){
        return new ResponseEntity<>(
                this.usuarioService.getAllInactive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Usuario>> getById(
            @PathVariable("id")
            Long id){
        return new ResponseEntity<>(
                this.usuarioService.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Usuario>> insert(
            @RequestBody @Valid UsuarioDto usuarioDto,
            @Valid BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.usuarioService.insert(usuarioDto.getUsuario()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Usuario>> update(
            @RequestBody UsuarioDto usuarioDto,
            @Valid BindingResult result
    ){
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.usuarioService.update(usuarioDto.getUsuario()),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> enableDisable(
            @PathVariable("id") Long id,
            @RequestBody UsuarioDto usuarioDto
    ){
        return new ResponseEntity<>(
                this.usuarioService.changeStatus(usuarioDto.getUsuario()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> deleteUser(@PathVariable Long id) {
        CustomResponse<Boolean> response = usuarioService.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
}
