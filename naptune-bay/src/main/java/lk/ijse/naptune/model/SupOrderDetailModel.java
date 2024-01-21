package lk.ijse.naptune.model;

import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.SupOrderDetailDto;
import lk.ijse.naptune.dto.SupOrderDto;
import lk.ijse.naptune.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SupOrderDetailModel {
    public boolean save(SupOrderDto supOrderDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO suppliers_order_detail  VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            for (int i = 0; i < supOrderDto.getOrderList().size(); i++) {
                ps.setString(1, supOrderDto.getSupItemId());
                ps.setString(2, supOrderDto.getOrderList().get(i)[0]);
                ps.setString(3, supOrderDto.getOrderList().get(i)[1]);

                int update = ps.executeUpdate();

                if (update == 0) return false;
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean saveOrderDetail(String supItemId, List<CartTm> tmList) throws SQLException {
        Boolean ok = false;
        for (CartTm cartTm : tmList) {

            try {
                Connection connection = DbConnection.getInstance().getConnection();

                String sql = "INSERT INTO suppliers_order_detail  VALUES(?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(sql);


                ps.setString(1, supItemId);
                ps.setString(2, cartTm.getCode());
                ps.setString(3, String.valueOf(cartTm.getQty()));

                ok = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return ok;
    }

    public boolean saeOrderDetail(String supItemId, CartTm Tm) throws SQLException {
        Boolean ok = false;

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO suppliers_order_detail  VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);


                ps.setString(1, supItemId);
                ps.setString(2, Tm.getCode());
                ps.setString(3, String.valueOf(Tm.getQty()));

                ok = ps.executeUpdate() > 0;
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ok;
    }
}
