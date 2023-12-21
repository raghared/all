package employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import employee.dto.EmployeeRequest;
import employee.dto.EmployeeResponse;
import employee.exception.EmployeeNotFoundException;
import employee.model.Employee;
import employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final List<EmployeeResponse> list = new ArrayList<>();
	private final EmployeeRepository employeeRepository;
	@Transactional
	public EmployeeResponse makeEmployee(EmployeeRequest request) {
		return EmployeeMapper.MAPPER.modelEntityToModelDto(employeeRepository.save(EmployeeMapper.MAPPER.modelDtoToModelEntity(request)));
	}

	public List<EmployeeResponse> search(String keyword) {
		var search = employeeRepository.search(keyword);
		return EmployeeMapper.MAPPER.modelEntitiesToModelDtos(search);
	}

	/*public List<EmployeeResponse> searchKeywords(String keywords) {
		var search = employeeRepository.searchByKeywords(keywords);
		return EmployeeMapper.MAPPER.modelEntitiesToModelDtos(search);
	}*/

	public EmployeeResponse searchByEmail(String email) {
		Optional<Employee> email2 = employeeRepository.getEmail(email);
		boolean flag;
		flag = email2.isPresent();
		if (flag){
			return EmployeeMapper.MAPPER.modelEntityToModelDto(email2.get());
		}
		return null;
	}

	@Transactional
	public EmployeeResponse updateEmp(long id, EmployeeRequest employeeRequest) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Please Find the Valid Id:" + id));

		if (employeeRequest.getEmail() != null && !employeeRequest.getEmail().isEmpty()) {
			existingEmployee.setEmail(employeeRequest.getEmail());
		}
		// Save the updated employee to the database
		Employee update = employeeRepository.save(existingEmployee);
		return EmployeeMapper.MAPPER.modelEntityToModelDto(update);
	}

	public EmployeeResponse fetchEmployeeById(long id) {
		var employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Please Find the Valid Id:" + id));
		return EmployeeMapper.MAPPER.modelEntityToModelDto(employee);
	}

	public List<EmployeeResponse> fetchAllEmployees() {
		var allEmployees = employeeRepository.findAll();
		return EmployeeMapper.MAPPER.modelEntitiesToModelDtos(allEmployees);
	}

	public EmployeeResponse patchEmployeeByFields(long id, Map<String, Object> fields) {
		var existingEmp = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Please Find the Valid Id:" + id));
		fields.forEach((k, v) -> {
			try {
				existingEmp.getClass().getDeclaredField(k).set(existingEmp, v);
			} catch (ReflectiveOperationException e) {
				throw new RuntimeException(e);
			}
		});
		return EmployeeMapper.MAPPER.modelEntityToModelDto(employeeRepository.save(existingEmp));
	}

	public boolean deleteEmployeeById(long id) {
		if (!employeeRepository.existsById(id)) {
			return false;
		}
		employeeRepository.deleteById(id);
		return true;
	}
}
