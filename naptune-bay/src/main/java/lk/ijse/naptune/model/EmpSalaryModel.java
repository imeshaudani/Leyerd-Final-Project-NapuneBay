package lk.ijse.naptune.model;

import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.EmpSalaryDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpSalaryModel {

    public boolean save(EmpSalaryDto empSalaryDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO salary VALUES( ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, empSalaryDto.getSalaryId());
            ps.setString(2, empSalaryDto.getAmount());
            ps.setString(3, empSalaryDto.getMonth());
            ps.setString(4, empSalaryDto.getEmpId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public EmpSalaryDto search(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM salary WHERE salaryId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            EmpSalaryDto empSalaryDto = new EmpSalaryDto();

            if (resultSet.next()) {
                empSalaryDto.setSalaryId(resultSet.getString(1));
                empSalaryDto.setAmount(resultSet.getString(2));
                empSalaryDto.setMonth(resultSet.getString(3));
            }

            return empSalaryDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(EmpSalaryDto empSalaryDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE salary SET  amount = ?, month = ? WHERE salaryId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, empSalaryDto.getAmount());
            ps.setString(2, empSalaryDto.getMonth());
            ps.setString(3, empSalaryDto.getSalaryId());

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

            String sql = "DELETE FROM salary WHERE salaryId = ?";

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

        String sql = "SELECT * FROM salary";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<EmpSalaryDto> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new EmpSalaryDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return false;
    }

    public ArrayList<EmpSalaryDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM salary";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<EmpSalaryDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            EmpSalaryDto dto = new EmpSalaryDto();

            dto.setSalaryId(resultSet.getString(1));
            dto.setAmount(resultSet.getString(2));
            dto.setMonth(resultSet.getString(3));

            list.add(dto);
        }
        return list;
    }
}

