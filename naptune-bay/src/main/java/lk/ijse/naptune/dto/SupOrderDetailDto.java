package lk.ijse.naptune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupOrderDetailDto {
    private String supItemId;
    private String stockId;
    private int qty;

}
