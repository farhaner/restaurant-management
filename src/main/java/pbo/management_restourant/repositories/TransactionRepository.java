package pbo.management_restourant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import pbo.management_restourant.models.BranchModel;
import pbo.management_restourant.models.TransactionModel;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionModel, String> {

    //    @Query(value =
//            "select * " +
//                    "from transaction t " +
//                    "         join menu m on t.menu_id = m.menu_id " +
//                    "         join branch b on t.branch_id = b.branch_id " +
//                    "         join employee e on t.employee_id = e.employee_id " +
//                    "where transaction_id = :transactionId"
//            , nativeQuery = true)
//    List<TransactionModel> getAll(String transactionId);
    @Query(value =
            "SELECT t.transaction_id AS transactionId, " +
                    "       t.transaction_created_at AS transactionCreatedAt, " +
                    "       t.transaction_updated_at AS transactionUpdatedAt, " +
                    "       b.branch_name AS branchName, " +
                    "       e.employee_name AS employeeName, " +
                    "       m.menu_id AS menuId, " +
                    "       m.menu_name AS menuName, " +
                    "       m.price AS menuPrice " +
                    "FROM transaction t " +
                    "JOIN menu m ON t.menu_id = m.menu_id " +
                    "JOIN branch b ON t.branch_id = b.branch_id " +
                    "JOIN employee e ON t.employee_id = e.employee_id " +
                    "WHERE t.transaction_id = :transactionId",
            nativeQuery = true)
    Object[] getAll(String transactionId);
}
