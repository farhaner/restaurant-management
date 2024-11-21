package pbo.management_restourant.dto.request;

import lombok.Data;

@Data
public class BranchRequest {

    private String branchCode;
    private String branchName;
    private String branchAddres;
<<<<<<< HEAD
    private Long noTelp;
=======
    private String noTelp;
>>>>>>> c5d3077aae92322763a20cc8a38d56244955313c
    private String status;
    private String keyword;
}
