package lk.ijse.naptune.bo.Custom;

import lk.ijse.naptune.bo.SuperBO;
import lk.ijse.naptune.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {

    boolean Save(StockDto dto) throws SQLException, ClassNotFoundException;

    StockDto search(String id) throws SQLException, ClassNotFoundException;

    boolean update(StockDto dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ArrayList<StockDto> getAllCustomers() throws SQLException, ClassNotFoundException;
}
