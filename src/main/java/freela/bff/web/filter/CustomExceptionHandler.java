package freela.bff.web.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freela.bff.domain.model.response.core.ErrorResponse;
import freela.bff.web.exceptions.GenericErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;

    public CustomExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("error", "Ocorreu um erro interno na API.");
        return modelAndView;
    }

    @ExceptionHandler(GenericErrorException.class)
    public ResponseEntity<ErrorResponse> handleGenericErrorException(GenericErrorException exception) throws JsonProcessingException {
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getErrorResponse());
    }

}

