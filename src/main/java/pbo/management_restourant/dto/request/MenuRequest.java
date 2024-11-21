package pbo.management_restourant.dto.request;

import lombok.Data;

@Data
public class MenuRequest {

    private String menuCode;
    private String menuName;
    private String category;
    private Long amount;
}
