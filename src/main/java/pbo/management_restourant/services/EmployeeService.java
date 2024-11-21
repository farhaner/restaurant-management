package pbo.management_restourant.services;

import org.apache.coyote.BadRequestException;
import pbo.management_restourant.dto.request.EmployeeRequest;
import pbo.management_restourant.dto.response.CommonResponse;

public interface EmployeeService {

    CommonResponse addEmployee(EmployeeRequest request);

    CommonResponse getAllEmployee();

    CommonResponse getAllEmployeeByKeyword(String keyword);

    CommonResponse updatedEmployeeById(EmployeeRequest request) throws BadRequestException;

    CommonResponse deleteEmployeeById(String id);
}
