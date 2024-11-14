package pbo.management_restourant.dto.request;

import lombok.Data;

import java.math.BigInteger;

@Data
public class BranchRequest {

    private String branchCode;
    private String branchName;
    private String branchAddres;
    private BigInteger noTelp;
    private String status;
}
