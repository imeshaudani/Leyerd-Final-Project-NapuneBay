package lk.ijse.naptune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupOrderDto {
    private String supItemId;
    private String name;
    private String date;
    private String time;
    private String supId;
    private ArrayList<String[]> orderList;
}
