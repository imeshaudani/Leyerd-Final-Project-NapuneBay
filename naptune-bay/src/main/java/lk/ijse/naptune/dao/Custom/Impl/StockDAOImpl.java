package lk.ijse.naptune.dao.Custom.Impl;

import lk.ijse.naptune.dao.Custom.StockDAO;
import lk.ijse.naptune.dao.SQLUtil;
import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.StockDto;
import lk.ijse.naptune.entity.Payment;
import lk.ijse.naptune.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {


    @Override
    public boolean save(Stock entity) throws SQLException {
      return SQLUtil.execute("INSERT INTO stocks VALUES(?, ?, ?, ?,? )",
                entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getUserName());
    }

    @Override
    public boolean update(Stock entity) throws SQLException {
        return SQLUtil.execute( "UPDATE stocks SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?",
                entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
    }


    @Override
    public Stock search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM stocks WHERE code = ?",id);
        rst.next();
        return new Stock( id +1, rst.getString(2),
                rst.getString(3), rst.getString(4), rst.getString(5));
    }



    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM stocks WHERE code = ?", id);
    }


    @Override
    public ArrayList<Stock> getAllCustomers() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM stocks");
        ArrayList<Stock> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Stock entity = new Stock(
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
