package pbo.management_restourant.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class MenuModel {

    @Id
    @Column(name = "MENU_ID", nullable = false, length = 32)
    private String menuId;

    @Column(name = "MENU_NAME", nullable = false, length = 100)
    private String menuName;

    @Column(name = "MENU_CATEGORY", nullable = false, length = 50)
    private String category;

    @Column(name = "PRICE", nullable = false, length = 100)
    private String price;

    @Column(name = "QUANTITY", nullable = false, length = 50)
    private String quantity;

    @Column(name = "MENU_CREATED_AT", nullable = false)
    private LocalDate createdAt;

    @Column(name = "MENU_UPDATED_AT", nullable = false)
    private LocalDate updatedAt;

}
