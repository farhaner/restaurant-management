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
@Table(name = "daftarmenu")
public class MenuModel {

    @Id
    @Column(name = "MENU_CODE", nullable = false, length = 32)
    private int menuCode;

    @Column(name = "MENU_NAME", nullable = false, length = 100)
    private String menuName;

    @Column(name = "CATEGORY", nullable = false, length = 150)
    private String category;

    @Column(name = "AMOUNT", nullable = false, length = 15)
    private int amount;
}
