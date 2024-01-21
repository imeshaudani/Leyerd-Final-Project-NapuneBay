package lk.ijse.naptune.model;

import lk.ijse.naptune.dao.Custom.Impl.CustomerDAOImpl;
import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.CustomerDto;
import lk.ijse.naptune.dto.tm.Customertm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    /*public boolean save(CustomerDto customerDto) {
 try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO customer VALUES(?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, customerDto.getCustId());
            ps.setString(2, customerDto.getName());
            ps.setString(3, customerDto.getAddress());
            ps.setString(4, customerDto.getTel());
            ps.setString(5, customerDto.getUserName());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        boolean isSaved = customerDAO.save(new CustomerDto(customerDto.getCustId(), customerDto.getName(), customerDto.getAddress(), customerDto.getTel(), customerDto.getUserName()));
        return isSaved;

    }

    public CustomerDto search(String id) {
try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM customer WHERE custId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            CustomerDto customerDto = new CustomerDto();

            if (resultSet.next()) {
                customerDto.setCustId(resultSet.getString(1));
                customerDto.setName(resultSet.getString(2));
                customerDto.setAddress(resultSet.getString(3));
                customerDto.setTel(resultSet.getString(4));
            }

            return customerDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        CustomerDto isSearch = customerDAO.search(id);
        return isSearch;


    }

    public boolean update(CustomerDto customerDto) {
try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE customer SET name = ?, address = ?, tel = ? WHERE custId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, customerDto.getName());
            ps.setString(2, customerDto.getAddress());
            ps.setString(3, customerDto.getTel());
            ps.setString(4, customerDto.getCustId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        boolean isSaved = customerDAO.update(new CustomerDto(customerDto.getCustId(), customerDto.getName(), customerDto.getAddress(), customerDto.getTel(), customerDto.getUserName()));
        return isSaved;


    }

    public boolean delete(String id) {
 try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "DELETE FROM customer WHERE custId = ?";

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

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        boolean isdelete = customerDAO.delete(id);
        return isdelete;
    }

 public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<CustomerDto> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new CustomerDto(
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


    public static List<CustomerDto> loadAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<CustomerDto> proList = new ArrayList<>();

        while (resultSet.next()) {
            proList.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return proList;
    }


    public ArrayList<CustomerDto> getAllCustomers() throws SQLException {
Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<CustomerDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            CustomerDto dto = new CustomerDto();

            dto.setCustId(resultSet.getString(1));
            dto.setName(resultSet.getString(2));
            dto.setAddress(resultSet.getString(3));
            dto.setTel(resultSet.getString(4));

            list.add(dto);
        }
        return list;

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        ArrayList<CustomerDto> allCustomer = (ArrayList<CustomerDto>) customerDAO.getAllCustomers();
        for (CustomerDto dto : allCustomer){
            new Customertm(
                    dto.getCustId(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getTel()
            );

        }
        return allCustomer;
    }*/
}
