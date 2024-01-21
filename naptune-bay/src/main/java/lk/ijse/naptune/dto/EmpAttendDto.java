package lk.ijse.naptune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmpAttendDto {
    private String attendanceId;
    private String date;
    private String count;
    private String emp_Id;
}
