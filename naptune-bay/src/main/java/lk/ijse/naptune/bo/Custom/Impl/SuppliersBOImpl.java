package lk.ijse.naptune.bo.Custom.Impl;

import lk.ijse.naptune.bo.Custom.SuppliersBO;
import lk.ijse.naptune.dao.Custom.Impl.SuppliersDAOImpl;
import lk.ijse.naptune.dao.Custom.SuppliersDAO;
import lk.ijse.naptune.dto.SuppliersDto;
import lk.ijse.naptune.entity.Suppliers;

import java.sql.SQLException;
import java.util.ArrayList;

public class SuppliersBOImpl implements SuppliersBO {

    SuppliersDAO suppliersDAO = new SuppliersDAOImpl();

    @Override
    public boolean save(SuppliersDto dto) throws SQLException, ClassNotFoundException{
        //customer business logic example
        return suppliersDAO.save(new Suppliers(dto.getId(),dto.getName(),dto.getAddress(), dto.getTel(), dto.getUserName()));
    }

    @Override
    public boolean update(SuppliersDto dto) throws SQLException, ClassNotFoundException {
        return suppliersDAO.update(new Suppliers(dto.getId(),dto.getName(),dto.getAddress(), dto.getTel(), dto.getUserName()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return suppliersDAO.delete(id);
    }

    @Override
    public SuppliersDto search(String id) throws SQLException, ClassNotFoundException {
        Suppliers suppliers= suppliersDAO.search(id);
        SuppliersDto suppliersDto = new SuppliersDto(suppliers.getId(), suppliers.getName(), suppliers.getAddress(), suppliers.getTel(), suppliers.getUserName());
        return suppliersDto;
    }

    @Override
    public ArrayList<SuppliersDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Suppliers> suppliers = suppliersDAO.getAllCustomers();
        ArrayList<SuppliersDto> suppliersDtos = new ArrayList<>();
        for (Suppliers supplier : suppliers) {
            suppliersDtos.add(new SuppliersDto(supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getTel(), supplier.getUserName()));
        }
        return suppliersDtos;
    }
}
