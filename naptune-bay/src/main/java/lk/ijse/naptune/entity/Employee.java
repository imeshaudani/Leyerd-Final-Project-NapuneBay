package lk.ijse.naptune.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Employee {
    private String emp_Id;
    private String name;
    private String address;
    private String tel;
    private String userName;
}
