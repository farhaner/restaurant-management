package pbo.management_restourant.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import pbo.management_restourant.dto.request.EmployeeRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.dto.response.EmployeeResponse;
import pbo.management_restourant.models.EmployeeModel;
import pbo.management_restourant.repositories.EmployeeRepository;
import pbo.management_restourant.services.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private static final LocalDate LOCALDATE = LocalDate.now();

    @Transactional
    @Override
    public CommonResponse addEmployee(EmployeeRequest request) {
        CommonResponse response = new CommonResponse();
        try {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            validateEmployeeRequest(request);

            Optional<EmployeeModel> existingEmployee = employeeRepository.findById(request.getId());
            if (existingEmployee.isEmpty()) {

                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId(request.getId());
                employeeModel.setName(request.getName());
                employeeModel.setPosition(request.getPosition());
                employeeModel.setSalary(request.getSalary());
                employeeModel.setStatus(request.getStatus());
                employeeModel.setJoinDate(LOCALDATE);
                employeeModel.setEmployeeCreatedAt(LOCALDATE);
                employeeModel.setEmployeeUpdatedAt(LOCALDATE);
                employeeRepository.save(employeeModel);

                employeeResponse.setId(request.getId());
                employeeResponse.setName(request.getName());
                employeeResponse.setPosition(request.getPosition());
                employeeResponse.setSalary(request.getSalary());
                employeeResponse.setStatus(request.getStatus());
                employeeResponse.setJoinedDate(LOCALDATE);
                employeeResponse.setCreatedAt(LOCALDATE);

                response.setData(employeeResponse);
                response.setMessage("New Employee Successfully added");
            } else {
                employeeResponse.setId(request.getId());
                employeeResponse.setName(request.getName());
                employeeResponse.setPosition(request.getPosition());
                employeeResponse.setSalary(request.getSalary());
                employeeResponse.setStatus(request.getStatus());
                employeeResponse.setJoinedDate(LOCALDATE);
                employeeResponse.setCreatedAt(LOCALDATE);

                response.setData(employeeResponse);
                response.setMessage("Employee already exists");
            }
        } catch (IllegalArgumentException | BadRequestException ex) {
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public CommonResponse getAllEmployee() {
        CommonResponse response = new CommonResponse();

        List<EmployeeModel> allEmployees = employeeRepository.findAll();
        if (allEmployees.isEmpty()) {
            response.setData(null);
            response.setMessage("No Employees Found");
        } else {
            response.setData(allEmployees);
            response.setMessage("All Employees successfully retrieved");
        }

        return response;
    }

    @Override
    public CommonResponse getAllEmployeeByKeyword(String keyword) {
        CommonResponse response = new CommonResponse();

        if (keyword == null || keyword.trim().isEmpty()) {
            response.setData(null);
            response.setMessage("Please enter the keyword");
            return response;
        }

        List<EmployeeModel> employeesByKeyword = employeeRepository.getAllEmployeeByKeyword(keyword);
        if (employeesByKeyword.isEmpty()) {
            response.setData(null);
            response.setMessage("No Employees found for the given keyword");
        } else {
            response.setData(employeesByKeyword);
            response.setMessage("Employees successfully retrieved for keyword: " + keyword);
        }

        return response;
    }

    @Transactional
    @Override
    public CommonResponse updatedEmployeeById(EmployeeRequest request) throws BadRequestException {
        CommonResponse response = new CommonResponse();
        try {
            if (request.getId() == null || request.getId().isEmpty()) {
                throw new BadRequestException("Employee Id cannot be null or empty");
            }

            Optional<EmployeeModel> existingEmployee = employeeRepository.findById(request.getId());
            if (existingEmployee.isPresent()) {
                Object[] updateEmployee = employeeRepository.updateEmployee(
                        request.getId(),
                        request.getExitDate(),
                        request.getJoinedDate(),
                        request.getName(),
                        request.getPosition(),
                        request.getSalary(),
                        request.getStatus()
                );
                if (updateEmployee != null) {
                    response.setData(updateEmployee[0]);
                    response.setMessage("Employee Successfully updated");
                } else {
                    response.setData(null);
                    response.setMessage("Employee update failed");
                }
            } else {
                response.setData(null);
                response.setMessage("Employee with the given ID not found");
            }
        } catch (BadRequestException ex) {
            response.setData(null);
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setData(null);
            response.setMessage("An unexpected error occurred: " + ex.getMessage());
        }
        return response;
    }

    @Override
    public CommonResponse deleteEmployeeById(String id) {
        CommonResponse response = new CommonResponse();

        Optional<EmployeeModel> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            response.setData(existingEmployee.get());
            response.setMessage("Employee Successfully deleted");
        } else {
            response.setData(null);
            response.setMessage("Employee Not Found");
        }

        return response;
    }

    private void validateEmployeeRequest(EmployeeRequest request) throws BadRequestException {
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        if (request.getId() == null || request.getId().isEmpty()) {
            throw new BadRequestException("Employee ID cannot be null or empty");
        }
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new BadRequestException("Employee Name cannot be null or empty");
        }
        if (request.getPosition() == null || request.getPosition().isEmpty()) {
            throw new BadRequestException("Employee Position cannot be null or empty");
        }
        if (request.getSalary() == null || request.getSalary().isEmpty()) {
            throw new BadRequestException("Employee Salary cannot be null or empty");
        }
        if (request.getStatus() == null || request.getStatus().isEmpty()) {
            throw new BadRequestException("Employee Status cannot be null or empty");
        }
    }
}

