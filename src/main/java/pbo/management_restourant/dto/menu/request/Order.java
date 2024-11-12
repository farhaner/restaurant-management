package pbo.management_restourant.dto.menu.request;

import lombok.Data;

@Data
public class Order {

    private Branch branchId;
    private String customerId;
    private String customerName;
    private String customerNoTelp;
    private Menu orderList;
    private String paymentMethod;
    private String notes;
    private String orderDate;
}
