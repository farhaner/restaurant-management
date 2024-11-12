package pbo.management_restourant.dto.menu.request;

import lombok.Data;

@Data
public class Branch {

    private String branchCode;
    private String branchName;
    private String branchAddres;
    private String noTelp;
    private String status;
    private String createdAt;
}
