package pbo.management_restourant.dto.response;

import lombok.Data;
import pbo.management_restourant.dto.request.EmployeeRequest;

@Data
public class TransactionResponse {
    private String transactionId;
    private EmployeeRequest employeeRequest;
}
