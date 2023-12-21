package employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String department;
    private int yearOfJoining;
    private String email;
    private double salary;
}
