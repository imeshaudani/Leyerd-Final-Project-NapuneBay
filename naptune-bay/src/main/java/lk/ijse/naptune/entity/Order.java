package lk.ijse.naptune.entity;

import lk.ijse.naptune.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Order extends OrderDto {
    private String orderId;
    private String Name;
    private  String date;
    private String details;
    private String custId;

}
