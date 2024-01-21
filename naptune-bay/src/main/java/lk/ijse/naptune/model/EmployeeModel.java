
package lk.ijse.naptune.model;

import lk.ijse.naptune.dao.Custom.Impl.EmployeeDAOImpl;
import lk.ijse.naptune.dto.EmployeeDto;
import lk.ijse.naptune.dto.tm.Employeetm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
   /* public boolean save(EmployeeDto employeeDto) {

try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, employeeDto.getEmp_Id());
            ps.setString(2, employeeDto.getName());
            ps.setString(3, employeeDto.getAddress());
            ps.setString(4, employeeDto.getTel());
            ps.setString(5, employeeDto.getUserName());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;*//*

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        boolean isSaved = employeeDAO.save(new EmployeeDto(employeeDto.getEmp_Id(), employeeDto.getName(), employeeDto.getAddress(), employeeDto.getTel(), employeeDto.getUserName()));
        return isSaved;
    }

    public EmployeeDto search(String id) {

try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM employee WHERE empId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            EmployeeDto employeeDto = new EmployeeDto();

            if (resultSet.next()) {
                employeeDto.setEmp_Id(resultSet.getString(1));
                employeeDto.setName(resultSet.getString(2));
                employeeDto.setAddress(resultSet.getString(3));
                employeeDto.setTel(resultSet.getString(4));
            }

            return employeeDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*//*

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        EmployeeDto isSearch = employeeDAO.search(id);
        return isSearch;
    }

    public boolean update(EmployeeDto employeeDto) {try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE employee SET name = ?, address = ?, tel = ? WHERE empId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, employeeDto.getName());
            ps.setString(2, employeeDto.getAddress());
            ps.setString(3, employeeDto.getTel());
            ps.setString(4, employeeDto.getEmp_Id());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;*//*

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        boolean isUpdate = employeeDAO.update(new EmployeeDto(employeeDto.getEmp_Id(), employeeDto.getName(), employeeDto.getAddress(), employeeDto.getTel(), employeeDto.getUserName()));
        return isUpdate;
    }


    public boolean delete(String id) {

 try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "DELETE FROM employee WHERE empId = ?";

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

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        boolean isDelete = employeeDAO.delete(id);
        return isDelete;
    }


    public List<EmployeeDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<EmployeeDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            EmployeeDto dto = new EmployeeDto();

            dto.setEmp_Id(resultSet.getString(1));
            dto.setName(resultSet.getString(2));
            dto.setAddress(resultSet.getString(3));
            dto.setTel(resultSet.getString(4));


            list.add(dto);
        }
        return list;*//*


        EmployeeDAOImpl employeeDAO= new EmployeeDAOImpl();
        ArrayList<EmployeeDto> allEmployee = (ArrayList<EmployeeDto>) employeeDAO.getAllCustomers();
        for (EmployeeDto dto : allEmployee){
            new Employeetm(
                    dto.getEmp_Id(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getTel()
            );
        }
        return allEmployee;
    }*/
}
