package lk.ijse.naptune.bo.Custom;

import lk.ijse.naptune.bo.SuperBO;
import lk.ijse.naptune.dto.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean save(OrderDto dto) throws SQLException, ClassNotFoundException;

    boolean update(OrderDto dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    OrderDto search(String id) throws SQLException, ClassNotFoundException;

    ArrayList<OrderDto> getAllCustomers() throws SQLException, ClassNotFoundException;
}
