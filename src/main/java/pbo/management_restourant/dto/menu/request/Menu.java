package pbo.management_restourant.dto.menu.request;

import lombok.Data;

@Data
public class Menu {

    private String menuCode;
    private String menuName;
    private String category;
    private String amount;
}
