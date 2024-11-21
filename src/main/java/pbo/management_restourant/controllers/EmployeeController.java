package pbo.management_restourant.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import pbo.management_restourant.dto.request.EmployeeRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.services.EmployeeService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/create")
    public CommonResponse createEmployee(@RequestBody EmployeeRequest request) {
        return employeeService.addEmployee(request);
    }

    @GetMapping(value = "/{keyword}")
    public CommonResponse getEmployeesByKeyword(@PathVariable String keyword) {
        return employeeService.getAllEmployeeByKeyword(keyword);
    }

    @PostMapping(value = "/get")
    public CommonResponse getEmployeeByKeyword(@RequestBody EmployeeRequest request) {
        return employeeService.getAllEmployeeByKeyword(request.getKeyword());
    }

    @GetMapping
    public CommonResponse getAllEmployees() {
        return employeeService.getAllEmployee();
    }

    @PutMapping(value = "/update")
    public CommonResponse updatedEmployeeById(@RequestBody EmployeeRequest request) throws BadRequestException {
        return employeeService.updatedEmployeeById(request);
    }

    @DeleteMapping(value = "/{id}")
    public CommonResponse deleteEmployee(@PathVariable String id) {
        return employeeService.deleteEmployeeById(id);
    }
}
