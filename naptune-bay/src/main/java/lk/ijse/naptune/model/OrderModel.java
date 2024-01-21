package lk.ijse.naptune.model;

import lk.ijse.naptune.dao.Custom.Impl.OrderDAOImpl;
import lk.ijse.naptune.dto.OrderDto;
import lk.ijse.naptune.dto.tm.Ordertm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    /*public boolean save(OrderDto orderDto) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO orders VALUES(?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, orderDto.getOrderId());
            ps.setString(2, orderDto.getName());
            ps.setString(3, orderDto.getDate());
            ps.setString(4, orderDto.getDetails());
            ps.setString(5, orderDto.getCustId());


            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;*//*
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        boolean isSaved = orderDAO.save(new OrderDto(orderDto.getOrderId(), orderDto.getName(), orderDto.getDate(), orderDto.getDetails(), orderDto.getCustId()));
        return isSaved;
    }


    public boolean update(OrderDto orderDto) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE orders SET  name = ?, date = ?, details = ?  WHERE orderId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);


            ps.setString(1, orderDto.getName());
            ps.setString(2, orderDto.getDate());
            ps.setString(3, orderDto.getDetails());
            ps.setString(4, orderDto.getOrderId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;*//*
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        boolean isUpdate = orderDAO.update(new OrderDto(orderDto.getOrderId(), orderDto.getName(), orderDto.getDate(), orderDto.getDetails(), orderDto.getCustId()));
        return isUpdate;
    }

    public boolean delete(String id) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "DELETE FROM orders WHERE orderId = ?";

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
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        boolean isSaved = orderDAO.delete(id);
        return isSaved;
    }

    *//*public boolean deleteOrders(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM orders";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<OrderDto> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new OrderDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)


                    )
            );
        }
        return false;
    }*//*

    public OrderDto search(String id) {
        *//*try {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM orders WHERE orderId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            OrderDto orderDto = new OrderDto();

            if (resultSet.next()) {
                orderDto.setOrderId(resultSet.getString(1));
                orderDto.setName(resultSet.getString(2));
                orderDto.setDate(resultSet.getString(3));
                orderDto.setDetails(resultSet.getString(4));

            }

            return orderDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*//*
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        OrderDto isSearch = orderDAO.search(id);
        return isSearch;
    }

    public List<OrderDto> getAllCustomers() throws SQLException {
        *//*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM orders";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<OrderDto> list = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            OrderDto dto = new OrderDto();

            dto.setOrderId(resultSet.getString(1));
            dto.setName(resultSet.getString(2));
            dto.setDate(resultSet.getString(3));
            dto.setDetails(resultSet.getString(4));


            list.add(dto);
        }
        return list;*//*

        OrderDAOImpl orderDAO = new OrderDAOImpl();
        ArrayList<OrderDto> allOrders = (ArrayList<OrderDto>) orderDAO.getAllCustomers();
        for (OrderDto dto : allOrders){
            new Ordertm(
                    dto.getOrderId(),
                    dto.getName(),
                    dto.getDate(),
                    dto.getDetails()
            );
        }
        return allOrders;

    }*/
}
