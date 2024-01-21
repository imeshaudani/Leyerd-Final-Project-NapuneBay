package lk.ijse.naptune.dao.Custom.Impl;

import lk.ijse.naptune.dao.Custom.CustomerDAO;
import lk.ijse.naptune.dao.SQLUtil;
import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.CustomerDto;
import lk.ijse.naptune.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ?)",
                entity.getCustId(),entity.getName(),entity.getAddress(),entity.getTel(),entity.getUserName());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE custId = ?",id);
        rst.next();
        return new Customer(id + 1, rst.getString(2),
                rst.getString(3), rst.getString(4), rst.getString(5));
    }



    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name = ?, address = ?, tel = ? WHERE custId = ?",
                 entity.getName(), entity.getAddress(),entity.getTel(),entity.getCustId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE custId = ?", id);
    }


    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Customer entity = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5));
            allCustomer.add(entity);
        }
        return allCustomer;
    }
}
