package pbo.management_restourant.dto.menu.response;

import lombok.Data;
import pbo.management_restourant.dto.menu.request.Menu;

@Data
public class Customer {

    private String customerName;
    private Menu customerOrder;
    private String customerOrderDate;
}
