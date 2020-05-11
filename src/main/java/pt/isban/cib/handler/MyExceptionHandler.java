package pt.isban.cib.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pt.isban.cib.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            NotFoundException.class
    })

    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                "Exception Handler Error");

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    class ApiError {

        private HttpStatus status;
        private String message;
        private List<String> errors;

        public ApiError(HttpStatus status, String message, List<String> errors) {
            super();
            this.status = status;
            this.message = message;
            this.errors = errors;
        }

        public ApiError(HttpStatus status, String message, String error) {
            super();
            this.status = status;
            this.message = message;
            errors = Arrays.asList(error);
        }

        public HttpStatus getStatus() {
            return this.status;
        }

        public String getMessage() {
            return this.message;
        }

        public List<String> getErrors() {
            return this.errors;
        }
    }
}
