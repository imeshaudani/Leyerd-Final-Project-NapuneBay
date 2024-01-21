package lk.ijse.naptune.model;

import lk.ijse.naptune.dao.Custom.Impl.SuppliersDAOImpl;
import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.SuppliersDto;
import lk.ijse.naptune.dto.tm.Supplierstm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersModel {
    public static SuppliersDto search(String supId) {
        return null;
    }

    /* public boolean save(SuppliersDto suppliersDto) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO suppliers VALUES(?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, suppliersDto.getId());
            ps.setString(2, suppliersDto.getName());
            ps.setString(3, suppliersDto.getAddress());
            ps.setString(4, suppliersDto.getTel());
            ps.setString(5, suppliersDto.getUserName());



            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;*//*
        SuppliersDAOImpl suppliersDAO = new SuppliersDAOImpl();
        boolean isSave = suppliersDAO.save(new SuppliersDto(suppliersDto.getId(), suppliersDto.getName(), suppliersDto.getAddress(), suppliersDto.getTel(), suppliersDto.getUserName()));
        return isSave;
    }

    public static SuppliersDto search(String id) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM suppliers WHERE supId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            SuppliersDto suppliersDto = new SuppliersDto();

            if (resultSet.next()) {
                suppliersDto.setId(resultSet.getString(1));
                suppliersDto.setName(resultSet.getString(2));
                suppliersDto.setAddress(resultSet.getString(3));
                suppliersDto.setTel(resultSet.getString(4));
            }

            return suppliersDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*//*
        SuppliersDAOImpl suppliersDAO = new SuppliersDAOImpl();
        SuppliersDto isSeacrch = suppliersDAO.search(id);
        return isSeacrch;
    }

    public boolean update(SuppliersDto suppliersDto) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE suppliers SET supName = ?, address = ?, tel = ? WHERE supId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, suppliersDto.getName());
            ps.setString(2, suppliersDto.getAddress());
            ps.setString(3, suppliersDto.getTel());
            ps.setString(4, suppliersDto.getId());


            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;*//*

        SuppliersDAOImpl suppliersDAO = new SuppliersDAOImpl();
        boolean isUpdate = suppliersDAO.update(new SuppliersDto(suppliersDto.getId(), suppliersDto.getName(), suppliersDto.getAddress(), suppliersDto.getTel(), suppliersDto.getUserName()));
        return isUpdate;
    }

    public boolean delete(String id) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "DELETE FROM suppliers WHERE supId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;*//*
        SuppliersDAOImpl suppliersDAO = new SuppliersDAOImpl();
        boolean isDelete = suppliersDAO.delete(id);
        return isDelete;
    }

    public ArrayList<SuppliersDto> getAllCustomers() throws SQLException {
        *//*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM suppliers";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<SuppliersDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            SuppliersDto dto = new SuppliersDto();

            dto.setId(resultSet.getString(1));
            dto.setName(resultSet.getString(2));
            dto.setAddress(resultSet.getString(3));
            dto.setTel(resultSet.getString(4));


            list.add(dto);
        }
        return list;*//*
        SuppliersDAOImpl suppliersDAO = new SuppliersDAOImpl();
        ArrayList<SuppliersDto> allSuppliers = (ArrayList<SuppliersDto>) suppliersDAO.getAllCustomers();
        for (SuppliersDto dto : allSuppliers){
            new Supplierstm(
                    dto.getId(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getTel()
            );
        }
        return allSuppliers;

    }*/

    public ArrayList<String> getAllSuppliersId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT supId from suppliers";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<String> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));

        }
        return list;
    }

    public List<SuppliersDto> loadAllSuppliers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM suppliers";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<SuppliersDto> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new SuppliersDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return cusList;
    }

    public SuppliersDto searchSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM suppliers WHERE supId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        SuppliersDto dto = null;

        if(resultSet.next()) {
            String supId = resultSet.getString(1);
            String supName = resultSet.getString(2);
            String supAddress = resultSet.getString(3);
            String supTel = resultSet.getString(4);
            String supUserName = resultSet.getString(5);

            dto = new SuppliersDto(supId, supName, supAddress, supTel, supUserName);
        }

        return dto;
    }
}


