package pbo.management_restourant.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties
public class BranchResponse {

    private String branchCode;
    private String branchName;
    private String branchAddres;
    private String noTelp;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
