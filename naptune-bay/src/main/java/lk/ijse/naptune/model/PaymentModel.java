package lk.ijse.naptune.model;

import lk.ijse.naptune.dao.Custom.Impl.PaymentDAOImpl;
import lk.ijse.naptune.dto.PaymentDto;
import lk.ijse.naptune.dto.tm.Employeetm;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    /*public boolean save(PaymentDto paymentDto) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO payment VALUES(?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, paymentDto.getPayId());
            ps.setString(2, paymentDto.getDate());
            ps.setString(3, paymentDto.getAmount());
            ps.setString(4, paymentDto.getOrderId());


            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;*//*
    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();

    boolean isSaved = paymentDAO.save(new PaymentDto(paymentDto.getPayId(), paymentDto.getDate(), paymentDto.getDate(), paymentDto.getOrderId()));
    return isSaved;

}

    public PaymentDto search(String id) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM payment WHERE payId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            PaymentDto paymentDto = new PaymentDto();

            if (resultSet.next()) {
                paymentDto.setPayId(resultSet.getString(1));
                paymentDto.setDate(resultSet.getString(2));
                paymentDto.setAmount(resultSet.getString(3));

            }

            return paymentDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*//*
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        PaymentDto isSearch = paymentDAO.search(id);
        return isSearch;
    }

    public boolean update(PaymentDto paymentDto) {
       *//* try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE payment SET date = ?, amount = ? WHERE payId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, paymentDto.getDate());
            ps.setString(2, paymentDto.getAmount());
            ps.setString(3, paymentDto.getPayId());


            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;*//*
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        boolean isUpdate = paymentDAO.save(new PaymentDto(paymentDto.getPayId(), paymentDto.getDate(), paymentDto.getDate(), paymentDto.getOrderId()));
        return isUpdate;
    }

    public boolean delete(String id) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "DELETE FROM payment WHERE payId = ?";

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
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        boolean isDelete = paymentDAO.delete(id);
        return isDelete;
    }

    public ArrayList<PaymentDto> getAllCustomers() throws SQLException {
        *//*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM payment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<PaymentDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            PaymentDto dto = new PaymentDto();

            dto.setPayId(resultSet.getString(1));
            dto.setDate(resultSet.getString(2));
            dto.setAmount(resultSet.getString(3));

            list.add(dto);
        }
        return list;*//*
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        ArrayList<PaymentDto> allPayment = (ArrayList<PaymentDto>) paymentDAO.getAllCustomers();
        for (PaymentDto dto : allPayment){
            new Employeetm(
                    dto.getPayId(),
                    dto.getDate(),
                    dto.getAmount(),
                    dto.getOrderId()
            );
        }
        return allPayment;
    }*/
}
