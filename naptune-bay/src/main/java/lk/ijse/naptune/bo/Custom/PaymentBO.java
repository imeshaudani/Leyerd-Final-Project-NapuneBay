package lk.ijse.naptune.bo.Custom;

import lk.ijse.naptune.bo.SuperBO;
import lk.ijse.naptune.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    boolean save(PaymentDto dto) throws SQLException, ClassNotFoundException;

    boolean update(PaymentDto dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    PaymentDto search(String id) throws SQLException, ClassNotFoundException;

    ArrayList<PaymentDto> getAllCustomers() throws SQLException, ClassNotFoundException;
}
