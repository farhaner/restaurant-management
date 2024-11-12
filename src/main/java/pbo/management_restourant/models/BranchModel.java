package pbo.management_restourant.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

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
    private int branchNoTelp;

    @ColumnDefault("curdate()")
    @Column(name = "BRANCH_CREATED_AT", nullable = false)
    private LocalDate branchCreatedAt;

}
