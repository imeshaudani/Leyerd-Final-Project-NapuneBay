package lk.ijse.naptune.dto;

import lk.ijse.naptune.dto.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOderDto {
    private String supItemId;
    private String supId;
    private LocalDate date;
    private List<CartTm> tmList = new ArrayList<>();
}
