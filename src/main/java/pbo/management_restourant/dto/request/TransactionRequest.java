package pbo.management_restourant.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TransactionRequest {
    private String orderId;
    private String branchId;
    private String employeeId;
//    private List<Menu> menuId;
//    private int quantity;
    //    private String menuName;
//    private int pricePerItem;
//    private int totalPrice;
    private String createdAt;
}
