package pbo.management_restourant.dto.request;

import lombok.Data;

@Data
public class TransactionRequest {
    private String transactionId;
    private String branchId;
    private String employeeId;
    private String menuId;
}
