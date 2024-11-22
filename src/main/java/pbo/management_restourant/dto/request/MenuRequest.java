package pbo.management_restourant.dto.request;

import lombok.Data;

@Data
public class MenuRequest {
    private String menuId;
    private String menuName;
    private String category;
    private String price;
    private String quantity;
    private String keyword;
}
