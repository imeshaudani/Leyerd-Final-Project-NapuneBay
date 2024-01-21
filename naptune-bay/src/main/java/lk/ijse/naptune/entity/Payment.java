package lk.ijse.naptune.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Payment {
    private String payId;
    private String date;
    private String amount;
    private String orderId;
}
