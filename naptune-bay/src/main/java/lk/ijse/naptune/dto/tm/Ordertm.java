package lk.ijse.naptune.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ordertm {
    private String orderId;
    private String Name;
    private  String date;
    private String details;
}
