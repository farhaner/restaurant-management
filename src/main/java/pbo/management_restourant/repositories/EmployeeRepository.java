package pbo.management_restourant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import pbo.management_restourant.models.EmployeeModel;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, String> {
    @Query(value =
            "SELECT * " +
                    "FROM employee " +
                    "WHERE CONCAT(employee_id, ' ', employee_name, ' ', employee_position, ' ', employee_salary, ' ', " +
                    "             employee_status, ' ', " +
                    "             DATE_FORMAT(employee_updated_at, '%Y-%m-%d'), ' ', DATE_FORMAT(employee_created_at, '%Y-%m-%d')) " +
                    "          LIKE LOWER(CONCAT('%', :keyword, '%'))"
            , nativeQuery = true)
    List<EmployeeModel> getAllEmployeeByKeyword(String keyword);

    @Procedure(procedureName = "update_employee")
    Object[] updateEmployee(@Param("e_employee_id") String employeeId,
                            @Param("e_exit_date") String exitDate,
                            @Param("e_joined_date") String joinedDate,
                            @Param("e_employee_name") String employeeName,
                            @Param("e_employee_position") String position,
                            @Param("e_employee_salary") String salary,
                            @Param("e_employee_status") String status);
}
