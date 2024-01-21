package lk.ijse.naptune.model;

import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.MenuDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    public boolean save(MenuDto menuDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO menu_item VALUES(?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, menuDto.getItemCode());
            ps.setString(2, menuDto.getName());
            ps.setString(3, menuDto.getPrice());
            ps.setString(4, menuDto.getOrderId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public MenuDto search(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM menu_item WHERE itemCode = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            MenuDto menuDto = new MenuDto();

            if (resultSet.next()) {
                menuDto.setItemCode(resultSet.getString(1));
                menuDto.setName(resultSet.getString(2));
                menuDto.setPrice(resultSet.getString(3));
            }

            return menuDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(MenuDto menuDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE menu_item SET name = ?, price = ?  WHERE itemCode = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, menuDto.getName());
            ps.setString(2, menuDto.getPrice());
            ps.setString(3, menuDto.getItemCode());

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

            String sql = "DELETE FROM menu_item WHERE itemCode = ?";

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

    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM menu_item";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<MenuDto> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new MenuDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)

                    )
            );
        }
        return false;
    }

    public static List<MenuDto> loadAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM menu_item";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<MenuDto> proList = new ArrayList<>();

        while (resultSet.next()) {
            proList.add(new MenuDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return proList;
    }


    public ArrayList<MenuDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM menu_item";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<MenuDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            MenuDto dto = new MenuDto();

            dto.setItemCode(resultSet.getString(1));
            dto.setName(resultSet.getString(2));
            dto.setPrice(resultSet.getString(3));

            list.add(dto);
        }
        return list;
    }
}
