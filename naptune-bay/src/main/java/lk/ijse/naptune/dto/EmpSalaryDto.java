package lk.ijse.naptune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmpSalaryDto {
    private String salaryId;
    private String amount;
    private String month;
    private String empId;

}
