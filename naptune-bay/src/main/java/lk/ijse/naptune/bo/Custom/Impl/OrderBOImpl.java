package lk.ijse.naptune.bo.Custom.Impl;

import lk.ijse.naptune.bo.Custom.OrderBO;
import lk.ijse.naptune.dao.Custom.Impl.OrderDAOImpl;
import lk.ijse.naptune.dao.Custom.OrderDAO;
import lk.ijse.naptune.dto.OrderDto;
import lk.ijse.naptune.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(dto.getOrderId(), dto.getName(), dto.getDate(), dto.getDetails(), dto.getCustId()));
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(dto.getOrderId(), dto.getName(), dto.getDate(), dto.getDetails(), dto.getCustId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public OrderDto search(String id) throws SQLException, ClassNotFoundException {
        Order order = orderDAO.search(id);
        OrderDto orderDto = new OrderDto(order.getOrderId(), order.getName(), order.getDate(), order.getDetails(), order.getCustId());
        return orderDto;
    }

    @Override
    public ArrayList<OrderDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Order> orders = orderDAO.getAllCustomers();
        ArrayList<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(new OrderDto(order.getOrderId(), order.getName(), order.getDate(), order.getDetails(), order.getCustId()));
        }
        return orderDtos;
    }
}
