package mx.edu.utez.biblioteca.modelo.archivos.FilesAlmacenar;

public class ArchivoResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public ArchivoResponse(String message) {
        super();
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
