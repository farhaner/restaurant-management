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
@Table(name = "employee")
public class EmployeeModel {

    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false, length = 32)
    private String id;

    @Column(name = "EMPLOYEE_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "EMPLOYEE_POSITION", nullable = false, length = 50)
    private String position;

    @Column(name = "EMPLOYEE_SALARY", nullable = false, length = 100)
    private String salary;

    @Column(name = "EMPLOYEE_STATUS", nullable = false, length = 100)
    private String status;

    @Column(name = "JOINED_DATE", nullable = false)
    private LocalDate joinDate;

    @Column(name = "EXIT_DATE")
    private LocalDate exitDate;

    @Column(name = "EMPLOYEE_CREATED_AT", nullable = false)
    private LocalDate employeeCreatedAt;

    @Column(name = "EMPLOYEE_UPDATED_AT", nullable = false)
    private LocalDate employeeUpdatedAt;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TransactionModel> transactions = new ArrayList<>();
}

