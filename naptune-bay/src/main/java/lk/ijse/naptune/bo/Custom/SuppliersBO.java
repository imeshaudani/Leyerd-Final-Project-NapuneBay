package lk.ijse.naptune.bo.Custom;

import lk.ijse.naptune.bo.SuperBO;
import lk.ijse.naptune.dto.SuppliersDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuppliersBO extends SuperBO {
    boolean save(SuppliersDto dto) throws SQLException, ClassNotFoundException;

    boolean update(SuppliersDto dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    SuppliersDto search(String id) throws SQLException, ClassNotFoundException;

    ArrayList<SuppliersDto> getAllCustomers() throws SQLException, ClassNotFoundException;
}
