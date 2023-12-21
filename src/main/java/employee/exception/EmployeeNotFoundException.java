package employee.exception;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EmployeeNotFoundException extends RuntimeException {

    String msg;
    public EmployeeNotFoundException(String msg){
        super(msg);
    }

    private static final DateTimeFormatter f1= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
    private static final DateTimeFormatter f2= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
}

