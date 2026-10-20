package mx.edu.utez.Usuarios_Tickets.exception.CustomException;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
