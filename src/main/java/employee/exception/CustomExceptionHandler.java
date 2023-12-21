package employee.exception;

import employee.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex) {
        return new ErrorResponse("Intenal server error", ex.getMessage());
    }

    @ExceptionHandler({EmployeeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return new ErrorResponse("employee is not found in the backend system ", ex.getMessage());
    }

    @ExceptionHandler({EmployeeFoundException.class})
    @ResponseStatus(HttpStatus.FOUND)
    public ErrorResponse handleEmployeeFoundException(EmployeeFoundException ex) {
        return new ErrorResponse("Try to add different details becase of employee is already existed", ex.getMessage());
    }

    @ExceptionHandler({InvalidInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidInputException(InvalidInputException ex) {
        return new ErrorResponse("Bad Request", ex.getMessage());
    }
}