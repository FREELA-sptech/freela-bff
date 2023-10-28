package freela.bff.web.exceptions;

import freela.bff.domain.model.response.core.ErrorResponse;

public class GenericErrorException extends RuntimeException {
    private ErrorResponse errorResponse;
    private int statusCode;

    public GenericErrorException(ErrorResponse errorResponse, int statusCode) {
        this.errorResponse = errorResponse;
        this.statusCode = statusCode;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
