package employee.mapping;

import employee.dto.EmployeeRequest;
import employee.dto.EmployeeResponse;
import employee.model.Employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapping {
/*
    public Employee mapEmployee(EmployeeRequest request) {
        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .gender(request.getGender())
                .department(request.getDepartment())
                .yearOfJoining(request.getYearOfJoining())
                .email(request.getEmail())
                .salary(request.getSalary())
                .build();
    }

    public EmployeeResponse mapResponse(Employee mapData) {
        return EmployeeResponse.builder()
                .id(mapData.getId())
                .firstName(mapData.getFirstName())
                .lastName(mapData.getLastName())
                .age(mapData.getAge())
                .gender(mapData.getGender())
                .department(mapData.getDepartment())
                .yearOfJoining(mapData.getYearOfJoining())
                .email(mapData.getEmail())
                .salary(mapData.getSalary())
                .build();
    }

    public Employee updateEmployee(EmployeeRequest employeeRequest, long id) {
    	return Employee.builder()
                .id(id)
                .lastName(employeeRequest.getLastName())
                .age(employeeRequest.getAge())
                .gender(employeeRequest.getGender())
                .department(employeeRequest.getDepartment())
                .yearOfJoining(employeeRequest.getYearOfJoining())
                .email(employeeRequest.getEmail())
                .salary(employeeRequest.getSalary()).build();
    }
       */
}
