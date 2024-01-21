package lk.ijse.naptune.bo.Custom;

import lk.ijse.naptune.bo.SuperBO;
import lk.ijse.naptune.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    EmployeeDto search(String id) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDto> getAllCustomers() throws SQLException, ClassNotFoundException;
}
