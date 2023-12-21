package employee.service;

import employee.dto.EmployeeRequest;
import employee.dto.EmployeeResponse;
import employee.mapping.EmployeeMapping;
import employee.model.Employee;
import employee.repository.EmployeeRepository;
import employee.utils.TestEmployeeUtils;
import jakarta.el.PropertyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository mockEmployeeRepository;
    @Mock
    private EmployeeMapping mockEmployeeMapping;

    private EmployeeService employeeServiceUnderTest;

    private TestEmployeeUtils testEmployeeUtils;

    @BeforeEach
    void setUp() {
        employeeServiceUnderTest = new EmployeeService(mockEmployeeRepository, mockEmployeeMapping);
        testEmployeeUtils= new TestEmployeeUtils();
    }

    @Test
    void testMakeEmployee() {
        // Setup
        final EmployeeRequest request = new EmployeeRequest("firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        final EmployeeResponse expectedResult = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);

        // Configure EmployeeMapping.mapEmployee(...).
        final Employee employee = new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapEmployee(new EmployeeRequest("firstName", "lastName", 21, "male","adm",2020,"email", 0.0)))
                .thenReturn(employee);

        // Configure EmployeeRepository.save(...).
        final Employee employee1 = new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeRepository.save(any(Employee.class))).thenReturn(employee1);

        // Configure EmployeeMapping.mapResponse(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(employeeResponse);

        // Run the test
        final EmployeeResponse result = employeeServiceUnderTest.makeEmployee(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testMakeEmployee_EmployeeRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        //final EmployeeRequest request = new EmployeeRequest("firstName", "lastName", "email", 0.0);

        // Configure EmployeeMapping.mapEmployee(...).
        //final Employee employee = new Employee(0L, "firstName", "lastName", "email", 0.0);
        when(mockEmployeeMapping.mapEmployee(testEmployeeUtils.mapRequest()))
                .thenReturn(testEmployeeUtils.mapEmployee());
        when(mockEmployeeRepository.save(any(Employee.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> employeeServiceUnderTest.makeEmployee(testEmployeeUtils.mapRequest()))
                .isInstanceOf(OptimisticLockingFailureException.class);

    }

    @Test
    void testSearch() {
        // Setup
        final List<EmployeeResponse> expectedResult = List.of(
                new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));

        // Configure EmployeeRepository.search(...).
        final List<Employee> employees = List.of(new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));
        when(mockEmployeeRepository.search("keyword")).thenReturn(employees);

        // Configure EmployeeMapping.mapResponse(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(employeeResponse);

        // Run the test
        final List<EmployeeResponse> result = employeeServiceUnderTest.search("keyword");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSearch_EmployeeRepositoryReturnsNoItems() {
        // Setup
        final List<EmployeeResponse> expectedResult = List.of(
                new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));
        when(mockEmployeeRepository.search("keyword")).thenReturn(Collections.emptyList());

        // Configure EmployeeMapping.mapResponse(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(employeeResponse);

        // Run the test
        final List<EmployeeResponse> result = employeeServiceUnderTest.search("keyword");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSearchByEmail() {
        // Setup
        final EmployeeResponse expectedResult = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);

        // Configure EmployeeRepository.getEmail(...).
        final Employee employee = new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeRepository.getEmail("email")).thenReturn(employee);

        // Configure EmployeeMapping.mapResponse(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(employeeResponse);

        // Run the test
        final EmployeeResponse result = employeeServiceUnderTest.searchByEmail("email");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateEmp() {
        // Setup
        final EmployeeRequest employeeRequest = new EmployeeRequest("firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        final EmployeeResponse expectedResult = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);

        // Configure EmployeeRepository.findById(...).
        final Optional<Employee> employee = Optional.of(new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));
        when(mockEmployeeRepository.findById(0L)).thenReturn(employee);

        // Configure EmployeeMapping.mapEmployee(...).
        final Employee employee1 = new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapEmployee(new EmployeeRequest("firstName", "lastName", 21, "male","adm",2020,"email", 0.0)))
                .thenReturn(employee1);

        // Configure EmployeeRepository.save(...).
        final Employee employee2 = new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeRepository.save(any(Employee.class))).thenReturn(employee2);

        // Configure EmployeeMapping.mapResponse(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(employeeResponse);

        // Run the test
        final EmployeeResponse result = employeeServiceUnderTest.updateEmp(0L, employeeRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateEmp_EmployeeRepositoryFindByIdReturnsAbsent() {
        // Setup
        final EmployeeRequest employeeRequest = new EmployeeRequest("firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> employeeServiceUnderTest.updateEmp(0L, employeeRequest))
                .isInstanceOf(PropertyNotFoundException.class);
    }

    @Test
    void testUpdateEmp_EmployeeRepositorySaveThrowsOptimisticLockingFailureException() {
        // Setup
        final EmployeeRequest employeeRequest = new EmployeeRequest("firstName", "lastName", 21, "male","adm",2020,"email", 0.0);

        // Configure EmployeeRepository.findById(...).
        final Optional<Employee> employee = Optional.of(new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));
        when(mockEmployeeRepository.findById(0L)).thenReturn(employee);

        // Configure EmployeeMapping.mapEmployee(...).
        final Employee employee1 = new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapEmployee(new EmployeeRequest("firstName", "lastName", 21, "male","adm",2020,"email", 0.0)))
                .thenReturn(employee1);

        when(mockEmployeeRepository.save(any(Employee.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> employeeServiceUnderTest.updateEmp(0L, employeeRequest))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testFetchEmployeeById() {
        final Optional<Employee> employee = Optional.of(testEmployeeUtils.mapEmployee());
        when(mockEmployeeRepository.findById(2L)).thenReturn(employee);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(testEmployeeUtils.mapResponse());

        // Run the test
        final EmployeeResponse result = employeeServiceUnderTest.fetchEmployeeById(2L);

        // Verify the results
        assertThat(result).isEqualTo(testEmployeeUtils.mapResponse());
        verify(mockEmployeeMapping, times(1)).mapResponse(testEmployeeUtils.mapEmployee());
        verify(mockEmployeeRepository, times(1)).findById(anyLong()).orElseGet(null);
    }

    @Test
    void testFetchEmployeeById_EmployeeRepositoryReturnsAbsent() {
        // Setup
        final EmployeeResponse expectedResult = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeRepository.findById(0L)).thenReturn(Optional.empty());

        // Configure EmployeeMapping.mapResponse(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(employeeResponse);

        // Run the test
        final EmployeeResponse result = employeeServiceUnderTest.fetchEmployeeById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFetchAllEmployees() {
        // Setup
        final List<EmployeeResponse> expectedResult = List.of(
                new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));

        // Configure EmployeeRepository.findAll(...).
        final List<Employee> employees = List.of(new Employee(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));
        when(mockEmployeeRepository.findAll()).thenReturn(employees);

        // Configure EmployeeMapping.mapResponse(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(employeeResponse);

        // Run the test
        final List<EmployeeResponse> result = employeeServiceUnderTest.fetchAllEmployees();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFetchAllEmployees_EmployeeRepositoryReturnsNoItems() {
        // Setup
        final List<EmployeeResponse> expectedResult = List.of(
                new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));
        when(mockEmployeeRepository.findAll()).thenReturn(Collections.emptyList());

        // Configure EmployeeMapping.mapResponse(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeMapping.mapResponse(any(Employee.class))).thenReturn(employeeResponse);

        // Run the test
        final List<EmployeeResponse> result = employeeServiceUnderTest.fetchAllEmployees();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteEmployeeById() {
        // Setup
        when(mockEmployeeRepository.existsById(0L)).thenReturn(false);

        // Run the test
        final boolean result = employeeServiceUnderTest.deleteEmployeeById(0L);

        // Verify the results
        assertThat(result).isFalse();
        verify(mockEmployeeRepository).deleteById(0L);
    }
}
