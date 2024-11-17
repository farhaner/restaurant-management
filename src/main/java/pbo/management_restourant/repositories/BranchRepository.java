package pbo.management_restourant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import pbo.management_restourant.models.BranchModel;

import java.util.List;

public interface BranchRepository extends JpaRepository<BranchModel, String> {

    @Query(value =
            "SELECT * " +
                    "FROM branch " +
                    "WHERE CONCAT(branch_id, ' ', branch_name, ' ', branch_location, ' ', branch_no_telp, ' ', DATE_FORMAT(branch_created_at, '%Y-%m-%d'), ' ', DATE_FORMAT(branch_updated_at, '%Y-%m-%d')) " +
                    "          LIKE LOWER(CONCAT('%', :keyword , '%'))",
            nativeQuery = true)
    List<BranchModel> getAllBranchByKeyword(String keyword);

    @Procedure(procedureName = "update_branch")
    Object[] updateBranch(@Param("p_branch_id") String branchId,
                          @Param("p_branch_location") String branchLocation,
                          @Param("p_branch_name") String branchName,
                          @Param("p_branch_no_telp") String branchNoTelp,
                          @Param("p_status") String status);

}
