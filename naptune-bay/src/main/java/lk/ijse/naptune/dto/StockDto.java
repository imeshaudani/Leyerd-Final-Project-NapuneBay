package lk.ijse.naptune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StockDto {
    private String code;
    private String description;
    private String unitPrice;
    private String qtyOnHand;
    private String userName;

}
