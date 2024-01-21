package lk.ijse.naptune.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmpAttendanceTm {
    private String attendanceId;
    private String date;
    private String count;
}
