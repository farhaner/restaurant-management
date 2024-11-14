package pbo.management_restourant.dto.response;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class BranchResponse {

    private String branchCode;
    private String branchName;
    private String branchAddres;
    private BigInteger noTelp;
    private String status;
    private Date createdAt;
}
