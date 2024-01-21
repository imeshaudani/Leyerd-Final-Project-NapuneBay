package lk.ijse.naptune.dao.Custom.Impl;

import lk.ijse.naptune.dao.Custom.OrderDAO;
import lk.ijse.naptune.dao.SQLUtil;
import lk.ijse.naptune.dto.OrderDto;
import lk.ijse.naptune.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?, ?, ?)",
                entity.getOrderId(),entity.getName(),entity.getDate(),entity.getDetails(),entity.getCustId());
    }



    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE orders SET  name = ?, date = ?, details = ?  WHERE orderId = ?",
               entity.getName(),entity.getDate(),entity.getDetails(), entity.getOrderId());
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders WHERE orderId = ?",id);
        rst.next();
        return new Order(id + "1", rst.getString(2),
                rst.getString(3), rst.getString(4), rst.getString(5));
    }
    

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM orders WHERE orderId = ?", id);
    }


    @Override
    public ArrayList<Order> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders");
        ArrayList<Order> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Order entity = new Order(
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
