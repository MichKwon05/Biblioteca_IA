package mx.edu.utez.biblioteca.controllers;

import jakarta.validation.Valid;
import mx.edu.utez.biblioteca.dtos.LibroDto;
import mx.edu.utez.biblioteca.modelo.libro.Libro;
import mx.edu.utez.biblioteca.services.LibroService;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/libro/")
@CrossOrigin(origins = {"*"})
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Libro>>> getAll(){
        return new ResponseEntity<>(
                this.libroService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getActive")
    public ResponseEntity<CustomResponse<List<Libro>>> getAllActive(){
        return new ResponseEntity<>(
                this.libroService.getAllActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getInactive")
    public ResponseEntity<CustomResponse<List<Libro>>> getAllInactive(){
        return new ResponseEntity<>(
                this.libroService.getAllInactive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Libro>> getById(
            @PathVariable("id")
            Long id){
        return new ResponseEntity<>(
                this.libroService.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Libro>> insert(@RequestBody @Valid LibroDto libroDto,
                                                        @Valid BindingResult result)
            throws Exception {
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.libroService.insert(libroDto.getLibro()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Libro>> update(
            @RequestBody LibroDto libroDto,
            @Valid BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.libroService.update(libroDto.getLibro()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> enableDisable(
            @PathVariable Long id,
            @RequestBody LibroDto libroDto
    ){
        return new ResponseEntity<>(
                this.libroService.changeStatus(libroDto.getLibro()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> deleteLibro(@PathVariable Long id) {
        CustomResponse<Boolean> response = libroService.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
