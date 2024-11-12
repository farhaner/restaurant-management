package pbo.management_restourant.dto.branch.request;

import lombok.Data;

@Data
public class BranchRequest {

    private String branchCode;
    private String branchName;
    private String branchLocation;
    private String branchNoTelp;
}
