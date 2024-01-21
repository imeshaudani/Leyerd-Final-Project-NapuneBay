package lk.ijse.naptune.dao.Custom.Impl;

import lk.ijse.naptune.dao.Custom.SuppliersDAO;
import lk.ijse.naptune.dao.SQLUtil;
import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.SuppliersDto;
import lk.ijse.naptune.entity.Payment;
import lk.ijse.naptune.entity.Suppliers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuppliersDAOImpl implements SuppliersDAO {

    @Override
    public boolean save(Suppliers entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO suppliers VALUES(?, ?, ?, ?)",
                entity.getId(),entity.getName(),entity.getAddress(),entity.getTel());
    }

    @Override
    public boolean update(Suppliers entity) throws SQLException {
        return SQLUtil.execute( "UPDATE suppliers SET supName = ?, address = ?, tel = ? WHERE supId = ?",
                entity.getName(),entity.getAddress(),entity.getTel(),entity.getId());
    }

    @Override
    public Suppliers search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM suppliers WHERE supId = ?",id);
        rst.next();
        return new Suppliers( id +1, rst.getString(2),
                rst.getString(3), rst.getString(4), rst.getString(5));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM suppliers WHERE supId = ?", id);
    }


    @Override
    public ArrayList<Suppliers> getAllCustomers() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM suppliers");
        ArrayList<Suppliers> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Suppliers entity = new Suppliers(
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
