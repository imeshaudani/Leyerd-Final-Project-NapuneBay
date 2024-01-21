package lk.ijse.naptune.bo.Custom.Impl;

import lk.ijse.naptune.bo.Custom.CustomerBO;
import lk.ijse.naptune.dao.Custom.CustomerDAO;
import lk.ijse.naptune.dao.Custom.Impl.CustomerDAOImpl;
import lk.ijse.naptune.dto.CustomerDto;
import lk.ijse.naptune.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean SaveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCustId(), dto.getName(), dto.getAddress(), dto.getTel(), dto.getUserName()));
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        CustomerDto customerDto = new CustomerDto(customer.getCustId(), customer.getName(), customer.getAddress(), customer.getTel(), customer.getUserName());
        return customerDto;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCustId(), dto.getName(), dto.getAddress(), dto.getTel(), dto.getUserName()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAllCustomers();
        ArrayList<CustomerDto> customerDTOS=new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(), customer.getTel(), customer.getUserName()));
        }
        return customerDTOS;
    }

}
