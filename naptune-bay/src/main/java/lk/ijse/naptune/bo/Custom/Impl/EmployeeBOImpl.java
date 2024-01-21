package lk.ijse.naptune.bo.Custom.Impl;

import lk.ijse.naptune.bo.Custom.EmployeeBO;
import lk.ijse.naptune.dao.Custom.EmployeeDAO;
import lk.ijse.naptune.dao.Custom.Impl.EmployeeDAOImpl;
import lk.ijse.naptune.dto.EmployeeDto;
import lk.ijse.naptune.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getEmp_Id(), dto.getName(), dto.getAddress(), dto.getTel(), dto.getUserName()));
    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmp_Id(), dto.getName(), dto.getAddress(), dto.getTel(), dto.getUserName()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(id);
        EmployeeDto employeeDto = new EmployeeDto(employee.getEmp_Id(), employee.getName(), employee.getAddress(), employee.getTel(), employee.getUserName());
        return employeeDto;
    }

    @Override
    public ArrayList<EmployeeDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAllCustomers();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDto(employee.getEmp_Id(), employee.getName(), employee.getAddress(), employee.getTel(), employee.getUserName()));
        }
        return employeeDtos;
    }
}
