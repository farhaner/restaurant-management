package pbo.management_restourant.dto.request;

import lombok.Data;

@Data
public class BranchRequest {

    private String branchCode;
    private String branchName;
    private String branchAddres;
    private Long noTelp;
    private String status;
}
