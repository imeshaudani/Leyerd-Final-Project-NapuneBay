package lk.ijse.naptune.model;

import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.PlaceOderDto;
import lk.ijse.naptune.dto.SupOrderDto;
import lk.ijse.naptune.dto.SuppliersDto;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SupOrderModel {

    public boolean save(SupOrderDto supOrderDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO suppliers_order  VALUES(?, ?, ?, ?,?)";
            System.out.println("sdfghj");
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, supOrderDto.getSupItemId());
            ps.setString(2, supOrderDto.getName());
            ps.setString(3, supOrderDto.getDate());
            ps.setString(4, supOrderDto.getTime());
            ps.setString(5, supOrderDto.getSupId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public SupOrderDto search(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM suppliers_order WHERE supItemId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            SupOrderDto supOrderDto = new SupOrderDto();

            if (resultSet.next()) {
                supOrderDto.setSupItemId(resultSet.getString(1));
                supOrderDto.setName(resultSet.getString(2));
                supOrderDto.setDate(resultSet.getString(3));
                supOrderDto.setTime(resultSet.getString(4));

            }

            return supOrderDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(SupOrderDto supOrderDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE suppliers_order SET name = ?, date = ?, time = ? WHERE supItemId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, supOrderDto.getName());
            ps.setString(2, supOrderDto.getDate());
            ps.setString(3, supOrderDto.getTime());
            ps.setString(4, supOrderDto.getSupItemId());


            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean delete(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "DELETE FROM suppliers_order WHERE supItemId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

   /*public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM suppliers_order";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<SupOrderDto> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new SupOrderDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return false;
    }

    public static List<SupOrderDto> loadAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM suppliers_order";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<SupOrderDto> proList = new ArrayList<>();

        while (resultSet.next()) {
            proList.add(new SupOrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return proList;
    }*/


    public ArrayList<SupOrderDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM suppliers_order";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<SupOrderDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            SupOrderDto dto = new SupOrderDto();

            dto.setSupItemId(resultSet.getString(1));
            dto.setName(resultSet.getString(2));
            dto.setDate(resultSet.getString(3));
            dto.setTime(resultSet.getString(4));


            list.add(dto);
        }
        return list;
    }

    public String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT supItemId FROM suppliers_order ORDER BY supItemId DESC LIMIT 1";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
        String currentOrderId = null;

        if (resultSet.next()) {
            currentOrderId = resultSet.getString(1);

        }
        return splitOrderId(currentOrderId);
    }

    private String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "O00" + id;
        }
        return "O001";
    }

    public static boolean saveOrder(String supItemId, String supId, LocalDate date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
            SuppliersDto dt = SuppliersModel.search(supId);
        Time time = Time.valueOf(LocalTime.now());
        String tm = String.valueOf(time);


        String sql = "INSERT INTO suppliers_order VALUES(?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supItemId);
        pstm.setString(2, dt.getName());
        pstm.setDate(3, Date.valueOf(date));
        pstm.setString(4, tm);
        pstm.setString(5, supId);

        return pstm.executeUpdate() > 0;
    }
}
