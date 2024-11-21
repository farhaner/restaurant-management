package pbo.management_restourant.dto.request;

import lombok.Data;

@Data
public class EmployeeRequest {

    private String id;
    private String name;
    private String position;
    private String salary;
    private String exitDate;
    private String joinedDate;
    private String status;
    private String keyword;

}
