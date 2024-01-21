package lk.ijse.naptune.bo.Custom;

import lk.ijse.naptune.bo.SuperBO;
import lk.ijse.naptune.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    boolean SaveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;
}
