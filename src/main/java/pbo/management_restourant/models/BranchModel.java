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
@Table(name = "branch")
public class BranchModel {

    @Id
    @Column(name = "BRANCH_ID", nullable = false, length = 32)
    private String branchId;

    @Column(name = "BRANCH_NAME", nullable = false, length = 100)
    private String branchName;

    @Column(name = "BRANCH_LOCATION", nullable = false, length = 150)
    private String branchLocation;

    @Column(name = "BRANCH_NO_TELP", nullable = false, length = 15)
    private String branchNoTelp;

    @Column(name = "STATUS", nullable = false, length = 15)
    private String status;

    @Column(name = "BRANCH_CREATED_AT", nullable = false)
    private LocalDate branchCreatedAt;

    @Column(name = "BRANCH_UPDATED_AT", nullable = false)
    private LocalDate branchUpdatedAt;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<TransactionModel> transactions = new ArrayList<>();

}
