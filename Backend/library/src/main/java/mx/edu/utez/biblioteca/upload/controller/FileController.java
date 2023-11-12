package mx.edu.utez.biblioteca.upload.controller;


import mx.edu.utez.biblioteca.modelo.archivos.FilesAlmacenar.ArchivoResponse;
import mx.edu.utez.biblioteca.modelo.archivos.FilesAlmacenar.File;
import mx.edu.utez.biblioteca.modelo.libro.Libro;
import mx.edu.utez.biblioteca.modelo.libro.LibroRepository;
import mx.edu.utez.biblioteca.services.LibroService;
import mx.edu.utez.biblioteca.upload.services.FileServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file")
@CrossOrigin({"*"})
public class FileController {

    @Autowired
    private FileServiceAPI fileServiceAPI;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroService libroService;

    @PostMapping("/upload")
    public ResponseEntity<ArchivoResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("titulo")String titulo,
            @RequestParam("type") String type
    )throws Exception{
        String filePath = fileServiceAPI.save(file,titulo + "Digital" + type + ".pdf");
        String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile", filePath).build().toString();

        Libro libro;

        libro = libroRepository.getLibroByTitulo(titulo);
        libro.setUrlReporte(url);

        libroService.update(libro);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ArchivoResponse("Archivo subido correctamente"));
    }

    @GetMapping("/get/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
		Resource resource = fileServiceAPI.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/all")
        public ResponseEntity<List<File>> getAllFiles() throws Exception {
		List<File> files = fileServiceAPI.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
			return new File(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

}
