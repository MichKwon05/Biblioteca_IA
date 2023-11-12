package mx.edu.utez.biblioteca.upload.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileServiceAPI {

    public String save(MultipartFile file, String fileName) throws Exception;

    String saveFinal(String fileName) throws Exception;

    public Resource load(String name) throws Exception;

    public void save(List<MultipartFile> files) throws Exception;

    public Stream<Path> loadAll() throws Exception;
}
