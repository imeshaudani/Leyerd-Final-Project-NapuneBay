package lk.ijse.naptune.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Stocktm {
    private String code;
    private String description;
    private String unitPrice;
    private String qtyOnHand;
    private Button btn;
}
