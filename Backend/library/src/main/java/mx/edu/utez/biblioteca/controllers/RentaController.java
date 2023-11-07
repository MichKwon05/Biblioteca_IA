package mx.edu.utez.biblioteca.controllers;

import jakarta.validation.Valid;
import mx.edu.utez.biblioteca.dtos.RentaDto;
import mx.edu.utez.biblioteca.modelo.renta.Renta;
import mx.edu.utez.biblioteca.services.RentaService;
import mx.edu.utez.biblioteca.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/renta/")
@CrossOrigin(origins = {"*"})
public class RentaController {

    @Autowired
    private RentaService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Renta>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getActive")
    public ResponseEntity<CustomResponse<List<Renta>>> getAllActive(){
        return new ResponseEntity<>(
                this.service.getAllActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getInactive")
    public ResponseEntity<CustomResponse<List<Renta>>> getAllInactive(){
        return new ResponseEntity<>(
                this.service.getAllInactive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Renta>> getById(
            @PathVariable("id")
            Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Renta>> insert(@RequestBody @Valid RentaDto rentaDto,
                                                        @Valid BindingResult result)
            throws Exception {
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(rentaDto.getRenta()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Renta>> update(
            @RequestBody RentaDto rentaDto,
            @Valid BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(rentaDto.getRenta()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> enableDisable(
            @PathVariable Long id,
            @RequestBody RentaDto rentaDto
    ){
        return new ResponseEntity<>(
                this.service.changeStatus(rentaDto.getRenta()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> deleteLibro(@PathVariable Long id) {
        CustomResponse<Boolean> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
