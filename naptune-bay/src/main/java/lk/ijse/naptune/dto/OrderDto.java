package lk.ijse.naptune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDto {
    private String orderId;
    private String Name;
    private  String date;
    private String details;
    private String custId;

}
