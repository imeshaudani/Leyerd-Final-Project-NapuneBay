package lk.ijse.naptune.dao.Custom.Impl;

import lk.ijse.naptune.dao.Custom.PaymentDAO;
import lk.ijse.naptune.dao.SQLUtil;
import lk.ijse.naptune.dto.PaymentDto;
import lk.ijse.naptune.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?)",
                entity.getPayId(),entity.getDate(),entity.getAmount());
    }

    @Override
    public boolean update(Payment entity) throws SQLException {
        return SQLUtil.execute( "UPDATE payment SET date = ?, amount = ? WHERE payId = ?",
                entity.getDate(),entity.getAmount(),entity.getPayId());
    }

    @Override
    public Payment search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment WHERE payId = ?",id);
        rst.next();
        return new Payment( id +"1", rst.getString(2),
                rst.getString(3), rst.getString(4));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM payment WHERE payId = ?", id);
    }

    @Override
    public ArrayList<Payment> getAllCustomers() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");
        ArrayList<Payment> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Payment entity = new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
            allCustomer.add(entity);
        }
        return allCustomer;
    }
}
