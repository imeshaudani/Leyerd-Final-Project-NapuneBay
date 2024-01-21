package lk.ijse.naptune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentDto {
    private String payId;
    private String date;
    private String amount;
    private String orderId;

}
