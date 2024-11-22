package pbo.management_restourant.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {
    private String transactionId;
    private String employeeName;
    private String branchName;
    private String menu;
    private LocalDate transactionCreatedAt;
    private LocalDate transactionUpdatedAt;
}
