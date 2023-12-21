/*package employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import employee.dto.EmployeeRequest;
import employee.dto.EmployeeResponse;
import employee.mapping.EmployeeMapping;
import employee.model.Employee;
import employee.service.EmployeeService;
import employee.utils.TestEmployeeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    EmployeeController employeeController;
    @Mock
    private EmployeeService mockEmployeeService;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    ObjectReader objectReader = objectMapper.reader();
    TestEmployeeUtils testEmployeeUtils;
    @BeforeEach
    public void setup() {
        employeeController = new EmployeeController(mockEmployeeService);
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        testEmployeeUtils=new TestEmployeeUtils();
    }

    @Test
    void testMakeEmployee() throws Exception {
        final EmployeeRequest request= EmployeeRequest.builder()
                .firstName("reyansh")
                .lastName("Gudumuru1")
                .email("raghava.rg4@gmail.com")
                .salary(15000.0)
                .build();
        Employee employee= Employee.builder()
                .id(2)
                .firstName("reyansh")
                .lastName("Gudumuru1")
                .email("raghava.rg4@gmail.com")
                .salary(15000.0)
                .build();
        final EmployeeResponse employeeResponse= EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .build();
        when(mockEmployeeService.makeEmployee(request)).thenReturn(employeeResponse);
        //String content = objectWriter.writeValueAsString(request);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/employees")
                        .content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        var actualResponse = objectReader.readValue(response.getContentAsString(), EmployeeResponse.class);
        assertThat(actualResponse).isEqualTo(employeeResponse);
    }

  /*  @Test
    void testGetEmployeeId1() throws Exception {
        // Setup
        // Configure EmployeeService.fetchEmployeeById(...).
        when(mockEmployeeService.fetchEmployeeById(2)).thenReturn(testEmployeeUtils.mapResponse());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employees/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(testEmployeeUtils).andReturn().getResponse();
        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(testEmployeeUtils.mapResponse());
    }*/

  /*  @Test
    void testGetEmployeeId2() throws Exception {
        // Setup
        // Configure EmployeeService.fetchAllEmployees(...).
        final List<EmployeeResponse> employeeResponses = List.of(
                new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0));
        when(mockEmployeeService.fetchAllEmployees()).thenReturn(employeeResponses);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetEmployeeId2_EmployeeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEmployeeService.fetchAllEmployees()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testUpdateEmployee() throws Exception {
        // Setup
        // Configure EmployeeService.updateEmp(...).
        final EmployeeResponse employeeResponse = new EmployeeResponse(0L, "firstName", "lastName", 21, "male","adm",2020,"email", 0.0);
        when(mockEmployeeService.updateEmp(0L, new EmployeeRequest("firstName", "lastName", 21, "male","adm",2020,"email", 0.0))).thenReturn(employeeResponse);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/employees/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDeleteEmployeeId() throws Exception {
        // Setup
        when(mockEmployeeService.deleteEmployeeById(0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/employees/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}*/