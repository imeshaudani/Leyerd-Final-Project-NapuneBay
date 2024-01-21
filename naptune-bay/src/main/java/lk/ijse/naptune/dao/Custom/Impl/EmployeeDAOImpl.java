package lk.ijse.naptune.dao.Custom.Impl;

import lk.ijse.naptune.dao.Custom.EmployeeDAO;
import lk.ijse.naptune.dao.SQLUtil;
import lk.ijse.naptune.dto.EmployeeDto;
import lk.ijse.naptune.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {


    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?)",
                entity.getEmp_Id(),entity.getName(),entity.getAddress(),entity.getTel(),entity.getUserName());
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE empId = ?",id);
        rst.next();
        return new Employee(id + "1", rst.getString(2),
                rst.getString(3), rst.getString(4), rst.getString(5));
    }


    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET name = ?, address = ?, tel = ? WHERE empId = ?",
                 entity.getName(), entity.getAddress(),entity.getTel(),entity.getEmp_Id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE empId = ?", id);
    }


    @Override
    public ArrayList<Employee> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Employee entity = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5));
            allCustomer.add(entity);
        }
        return allCustomer;
    }
}
