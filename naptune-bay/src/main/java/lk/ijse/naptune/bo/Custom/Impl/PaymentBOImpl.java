package lk.ijse.naptune.bo.Custom.Impl;

import lk.ijse.naptune.bo.Custom.PaymentBO;
import lk.ijse.naptune.dao.Custom.Impl.PaymentDAOImpl;
import lk.ijse.naptune.dao.Custom.PaymentDAO;
import lk.ijse.naptune.dto.OrderDto;
import lk.ijse.naptune.dto.PaymentDto;
import lk.ijse.naptune.entity.Order;
import lk.ijse.naptune.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public boolean save(PaymentDto dto) throws SQLException, ClassNotFoundException{
        //customer business logic example
        return paymentDAO.save(new Payment(dto.getPayId(),dto.getDate(),dto.getAmount(), dto.getOrderId()));
    }

    @Override
    public boolean update(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPayId(),dto.getDate(),dto.getAmount(), dto.getOrderId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDto search(String id) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.search(id);
        PaymentDto paymentDto = new PaymentDto(payment.getPayId(), payment.getDate(), payment.getAmount(), payment.getOrderId());
        return paymentDto;
    }

    @Override
    public ArrayList<PaymentDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = paymentDAO.getAllCustomers();
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto(payment.getPayId(), payment.getDate(), payment.getAmount(), payment.getOrderId()));
        }
        return paymentDtos;
    }
}
