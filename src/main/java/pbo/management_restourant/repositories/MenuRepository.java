package pbo.management_restourant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import pbo.management_restourant.models.BranchModel;
import pbo.management_restourant.models.MenuModel;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuModel, String> {

    @Query(value =
            "SELECT * " +
                    "FROM menu " +
                    "WHERE CONCAT(menu_id, ' ', menu_name, ' ', menu_category, ' ', price, ' ', " +
                    "             quantity, ' ', " +
                    "             DATE_FORMAT(menu_updated_at, '%Y-%m-%d'), ' ', DATE_FORMAT(menu_created_at, '%Y-%m-%d')) " +
                    "          LIKE LOWER(CONCAT('%', :keyword, '%'))"
            , nativeQuery = true)
    List<MenuModel> getAllMenuByKeyword(String keyword);

    @Procedure(procedureName = "update_menu")
    Object[] updateMenu(
            @Param("m_branch_id") String menuId,
            @Param("m_menu_name") String menuName,
            @Param("m_category") String category,
            @Param("m_price") String price,
            @Param("m_quantity") String quantity
    );

}
