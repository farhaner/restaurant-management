package pbo.management_restourant.dto.response;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class MenuResponse {

    private String branchCode;
    private String branchName;
    private String branchAddres;
    private BigInteger noTelp;
    private String status;
    private Date createdAt;
}
