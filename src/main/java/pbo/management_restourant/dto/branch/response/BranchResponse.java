package pbo.management_restourant.dto.branch.response;

import lombok.Data;

@Data
public class BranchResponse {

    private String branchCode;
    private String branchName;
    private String branchLocation;
    private String branchNoTelp;
    private String branchCreatedAt;
}
