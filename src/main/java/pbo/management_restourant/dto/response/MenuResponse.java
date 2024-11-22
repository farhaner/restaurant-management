package pbo.management_restourant.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuResponse {

    private String menuId;
    private String menuName;
    private String category;
    private String price;
    private String quantity;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
