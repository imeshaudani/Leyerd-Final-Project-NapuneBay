package lk.ijse.naptune.model;

import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.EmpAttendDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpAttendModel {
    public boolean save(EmpAttendDto empAttendDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO attendance VALUES(?, ?, ?, ? )";

            PreparedStatement ps = connection.prepareStatement(sql);


            ps.setString(1, empAttendDto.getAttendanceId());
            ps.setString(2, empAttendDto.getDate());
            ps.setString(3, empAttendDto.getCount());
            ps.setString(4, empAttendDto.getEmp_Id());


            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public EmpAttendDto search(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM attendance WHERE attendanceId  = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            EmpAttendDto empAttendDto = new EmpAttendDto();

            if (resultSet.next()) {
                empAttendDto.setAttendanceId(resultSet.getString(1));
                empAttendDto.setDate(resultSet.getString(2));
                empAttendDto.setCount(resultSet.getString(3));

            }

            return empAttendDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(EmpAttendDto empAttendDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE attendance SET date = ?, count = ?  WHERE attendanceId  = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, empAttendDto.getDate());
            ps.setString(2, empAttendDto.getCount());
            ps.setString(3, empAttendDto.getAttendanceId());

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

            String sql = "DELETE FROM attendance WHERE attendanceId  = ?";

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

    public ArrayList<EmpAttendDto> loadAllEmployee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM attendance";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<EmpAttendDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            EmpAttendDto dto = new EmpAttendDto();

            dto.setAttendanceId(resultSet.getString(1));
            dto.setDate(resultSet.getString(2));
            dto.setCount(resultSet.getString(3));

            list.add(dto);
        }
        return list;
    }
}
