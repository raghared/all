package employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public  class ErrorResponse {
    private String error;
        private String message;
    }