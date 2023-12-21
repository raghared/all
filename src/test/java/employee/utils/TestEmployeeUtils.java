package employee.utils;

import employee.dto.EmployeeRequest;
import employee.dto.EmployeeResponse;
import employee.model.Employee;

public class TestEmployeeUtils {
    public EmployeeRequest mapRequest() {
        return EmployeeRequest.builder()
                .firstName("reyansh")
                .lastName("Gudumuru1")
                .email("raghava.rg4@gmail.com")
                .salary(15000.0)
                .build();
    }

    public EmployeeResponse mapResponse() {
        return EmployeeResponse.builder()
                .id(mapEmployee().getId())
                .firstName(mapEmployee().getFirstName())
                .lastName(mapEmployee().getLastName())
                .email(mapEmployee().getEmail())
                .salary(mapEmployee().getSalary())
                .build();
    }

    public Employee mapEmployee() {
        return Employee.builder()
                .id(2L)
                .firstName("reyansh")
                .lastName("Gudumuru1")
                .email("raghava.rg4@gmail.com")
                .salary(15000.0)
                .build();
    }
}
