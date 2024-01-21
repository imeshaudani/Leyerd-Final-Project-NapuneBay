package lk.ijse.naptune.model;

import lk.ijse.naptune.dao.Custom.Impl.StockDAOImpl;
import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.StockDto;
import lk.ijse.naptune.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockModel {


    public ArrayList<StockDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM stocks";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<StockDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            StockDto dto = new StockDto();

            dto.setCode(resultSet.getString(1));
            dto.setDescription(resultSet.getString(2));
            dto.setUnitPrice(resultSet.getString(3));
            dto.setQtyOnHand(resultSet.getString(4));

            list.add(dto);
        }
        return list;
       /* StockDAOImpl stockDAO = new StockDAOImpl();
        ArrayList<StockDto> AllStock = (ArrayList<StockDto>) stockDAO.getAllCustomers();
        for (StockDto dto : AllStock){
            new Stocktm(
                    dto.getCode(),
                    dto.getDescription(),
                    dto.getUnitPrice(),
                    dto.getQtyOnHand(),

            );
        }
        return AllStock;*/
    }

    public boolean update(ArrayList<String[]> orderList) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE stocks SET qtyOnHand = qtyOnHand+? WHERE code = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            for (int i = 0; i < orderList.size(); i++) {
                ps.setString(1, (orderList.get(i)[1]));
                ps.setString(2, (orderList.get(i)[0]));

                int update = ps.executeUpdate();

                if (update == 0) {
                    return false;
                }

                return true;
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deleteStock(String code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM stock WHERE code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, code);

        return pstm.executeUpdate() > 0;
    }

    public List<StockDto> loadAllStock() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM stocks";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<StockDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new StockDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }

    public StockDto searchStock(String code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM stocks WHERE code = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet resultSet = pstm.executeQuery();

        StockDto dto = null;

        if(resultSet.next()) {
            dto = new StockDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
        }
        return dto;
    }

    public boolean updateStock(List<CartTm> tmList) throws SQLException {
        for (CartTm cartTm : tmList) {
            if(!updateStock(cartTm)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateStock(CartTm cartTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE stocks SET qtyOnHand = qtyOnHand - ? WHERE code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, cartTm.getQty());
        pstm.setString(2, cartTm.getCode());

        return pstm.executeUpdate() > 0;
    }
}
