package mapper;

import employee.dto.EmployeeRequest;
import employee.dto.EmployeeResponse;
import employee.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    //@Mapping(target = "id", ignore = true) // Ignore ID during mapping for create operations
    Employee modelDtoToModelEntity(EmployeeRequest request);

    EmployeeResponse modelEntityToModelDto(Employee employee);

    // Add a method to map a list of entities to a list of DTOs if needed
    List<EmployeeResponse> modelEntitiesToModelDtos(List<Employee> employees);

}
