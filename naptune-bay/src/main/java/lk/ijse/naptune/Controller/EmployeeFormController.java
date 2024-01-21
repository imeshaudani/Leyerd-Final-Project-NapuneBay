package lk.ijse.naptune.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.naptune.bo.BOFactory;
import lk.ijse.naptune.bo.Custom.CustomerBO;
import lk.ijse.naptune.bo.Custom.EmployeeBO;
import lk.ijse.naptune.dto.CustomerDto;
import lk.ijse.naptune.dto.EmployeeDto;
import lk.ijse.naptune.dto.tm.Employeetm;
import lk.ijse.naptune.model.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<Employeetm> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private AnchorPane root;

    EmployeeModel employeeModel = new EmployeeModel();

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.EMPLOYEE);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmpId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtEmpId.getText();


        try{
            boolean isDeleted = employeeBO.delete(id);

            if(isDeleted){
                tblEmployee.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Employee deleted! ").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {

        boolean isValidate = validateCustomer();
        if(isValidate) {

            EmployeeModel employeeModel = new EmployeeModel();
            EmployeeDto employeeDto = new EmployeeDto();

            employeeDto.setEmp_Id(txtEmpId.getText());
            employeeDto.setName(txtName.getText());
            employeeDto.setAddress(txtAddress.getText());
            employeeDto.setTel(txtTel.getText());
            employeeDto.setUserName(LoginFormController.userName);
            try {
                boolean save = employeeBO.save(employeeDto);

            if (save) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved !").show();
            }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "invalid employee details !").show();
        }


    }

    private boolean validateCustomer() {
        String id = txtEmpId.getText();
        boolean isCustomerIdValidate = Pattern.matches("[E][0-9]{3,}", id);
        if (!isCustomerIdValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Id !").show();
            return false;
        }

        String name = txtName.getText();
        boolean isCustomerNameValidate = Pattern.matches("[A-za-z]{3,}", name);
        if (!isCustomerNameValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Name !").show();
            return false;
        }

        String address = txtAddress.getText();
        boolean isCustomerAddressValidate = Pattern.matches("[A-za-z]{3,}", address);
        if (!isCustomerAddressValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Address !").show();
            return false;
        }

        String tel = txtTel.getText();
        boolean isCustomerTelValidate = Pattern.matches("\\d{10}", tel);
        if (!isCustomerTelValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Tel !").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        boolean isValidate = validateEmployee();
        if (isValidate) {

            EmployeeDto employeeDto = new EmployeeDto();

            employeeDto.setEmp_Id(txtEmpId.getText());
            employeeDto.setName(txtName.getText());
            employeeDto.setAddress(txtAddress.getText());
            employeeDto.setTel(txtTel.getText());
            try {

                boolean update = employeeBO.update(employeeDto);

                if (update) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee invalid,can't update!").show();
        }

    }

    private boolean validateEmployee() {
        String id = txtEmpId.getText();
        boolean isEmployeeIdValidate = Pattern.matches("[E][0-9]{3,}", id);
        if (!isEmployeeIdValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Id !").show();
            return false;
        }

        String name = txtName.getText();
        boolean isEmployeeNameValidate = Pattern.matches("[A-za-z]{3,}", name);
        if (!isEmployeeNameValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Name !").show();
            return false;
        }

        String address = txtAddress.getText();
        boolean isEmployeeAddressValidate = Pattern.matches("[A-za-z]{3,}", address);
        if (!isEmployeeAddressValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Address !").show();
            return false;
        }

        String tel = txtTel.getText();
        boolean isEmployeeTelValidate = Pattern.matches("\\d{10}", tel);
        if (!isEmployeeTelValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Tel !").show();
            return false;
        }

        return true;

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtEmpId.getText();

        EmployeeDto search = employeeBO.search(id);

        txtName.setText(search.getName());
        txtAddress.setText(search.getAddress());
        txtTel.setText(search.getTel());
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/empSalaryForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/empAttendanceForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public void initialize() throws SQLException, ClassNotFoundException {
              setCellValueFactory();
              getAllCustomers();
       }
       private void setCellValueFactory() {
           colEmpId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("emp_Id"));
           colName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
           colAddress.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("address"));
           colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

       }

       private void getAllCustomers() throws SQLException, ClassNotFoundException {
              //var model = new EmployeeModel();

              ObservableList<Employeetm> obList = FXCollections.observableArrayList();

           List<EmployeeDto> dtoList = employeeBO.getAllCustomers();

           for(EmployeeDto dto : dtoList) {
                  obList.add(new Employeetm(
                          dto.getEmp_Id(),
                          dto.getName(),
                          dto.getAddress(),
                          dto.getTel()
                  ));
           }
           tblEmployee.setItems(obList);
       }

}

