package pbo.management_restourant.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {

    private String id;
    private String name;
    private String position;
    private String salary;
    private String status;
    private LocalDate joinedDate;
    private LocalDate exitDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
