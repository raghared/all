package employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import employee.dto.EmployeeRequest;
import employee.dto.EmployeeResponse;
import employee.model.Employee;
import employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import employee.dto.EmployeeRequest;
import employee.dto.EmployeeResponse;
import employee.model.Employee;
import employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Employee", description = "Employee management APIs")
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(summary = "Create a new Employee", tags = { "employees", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        var employeeResponse = employeeService.makeEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
    }

    @Operation(summary = "Retrieve based on Keyword", tags = { "Employees", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no employees", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> search(@RequestParam String keyword) {
        var search = employeeService.search(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(search);
    }

    @Operation(
            summary = "Retrieve a Employee by Id",
            description = "Get a Employee object by specifying its id. The response is Employee object with id, firstName, lastName, email, and salary.",
            tags = { "employees", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = EmployeeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeId(@PathVariable Long id) {
        var search = employeeService.fetchEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(search);
    }

    // Add other controller methods as needed

    @Operation(summary = "Update a Employee by Id", tags = { "employees", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping("{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable long id, @RequestBody EmployeeRequest employeeRequest) {
        var employeeResponse = employeeService.updateEmp(id, employeeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);

    }

    @Operation(summary = "patch a Employee by Id", tags = { "employee", "patch" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PatchMapping("{id}")
    public ResponseEntity<EmployeeResponse> partialEmployee(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        var employeeResponse = employeeService.patchEmployeeByFields(id,fields);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);

    }
    @Operation(summary = "Delete a Employee by Id", tags = { "employees", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployeeId(@PathVariable long id) {
        var deleted = employeeService.deleteEmployeeById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

   /* @PutMapping("{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeByFields(@PathVariable long id, Map<String, Object> fields) {

        var employeeResponse = employeeService.updateEmployeeByFields(id, fields);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);

    }*/
}
