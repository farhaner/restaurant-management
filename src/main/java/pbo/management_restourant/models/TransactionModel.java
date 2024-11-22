package pbo.management_restourant.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class TransactionModel {

    @Id
    @Column(name = "TRANSACTION_ID", nullable = false, length = 32)
    private String transactionId;

    @Column(name = "TRANSACTION_CREATED_AT", nullable = false)
    private LocalDate transactionCreatedAt;

    @Column(name = "TRANSACTION_UPDATED_AT", nullable = false)
    private LocalDate transactionUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    private EmployeeModel employee;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchModel branch;

    @ManyToOne
    @JoinColumn(name = "MENU_ID", nullable = false)
    private MenuModel menu;

//    @ManyToMany
//    @JoinTable(
//            name = "transaction_menu",
//            joinColumns = @JoinColumn(name = "TRANSACTION_ID"),
//            inverseJoinColumns = @JoinColumn(name = "MENU_ID")
//    )
//    private List<MenuModel> menu = new ArrayList<>();
}
