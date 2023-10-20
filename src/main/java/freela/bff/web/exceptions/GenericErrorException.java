package freela.bff.web.exceptions;

public class GenericErrorException {
    private String message;

    public GenericErrorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
