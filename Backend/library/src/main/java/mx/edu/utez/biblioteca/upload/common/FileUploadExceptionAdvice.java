package mx.edu.utez.biblioteca.upload.common;

import mx.edu.utez.biblioteca.modelo.archivos.FilesAlmacenar.ArchivoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.file.FileAlreadyExistsException;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ArchivoResponse> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArchivoResponse("Verifica el tamaño de los archivos"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ArchivoResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArchivoResponse("Error generico" + ex.getMessage()));
    }
    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<ArchivoResponse> handleFileAlreadyExistsException(FileAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArchivoResponse("Nombre de archivo invalido"));
    }
}
