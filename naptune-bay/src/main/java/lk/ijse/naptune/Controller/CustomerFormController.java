package lk.ijse.naptune.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.naptune.bo.BOFactory;
import lk.ijse.naptune.bo.Custom.CustomerBO;
import lk.ijse.naptune.dto.CustomerDto;
import lk.ijse.naptune.dto.EmployeeDto;
import lk.ijse.naptune.dto.tm.Customertm;
import lk.ijse.naptune.model.CustomerModel;



import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {
       @FXML
       private TableColumn<?, ?> colAddress;

       @FXML
       private TableColumn<?,?> colCusId;

       @FXML
       private TableColumn<?, ?> colName;

       @FXML
       private TableColumn<?, ?> colTel;


       @FXML
       private TextField txtAddress;

       @FXML
       private TextField txtId;

       @FXML
       private TextField txtName;

       @FXML
       private TextField txtTel;

       @FXML
       public TableView<Customertm> tblCustomer;
       CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.CUSTOMER);

       CustomerModel customerModel = new CustomerModel();

       public void initialize() throws ClassNotFoundException {
              setCellValueFactory();
              getAllCustomer();
       }

       @FXML
       void btnClearOnAction(ActionEvent event) {
              txtId.clear();
              txtName.clear();
              txtAddress.clear();
              txtTel.clear();
       }

       @FXML
       void btnDeleteOnAction(ActionEvent event) {
              String id = txtId.getText();


              try{
                     boolean isDeleted = customerBO.deleteCustomer(id);

                     if(isDeleted){
                            tblCustomer.refresh();
                            new Alert(Alert.AlertType.CONFIRMATION,"customer deleted! ").show();
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
              if (isValidate) {

                     //CustomerModel customerModel = new CustomerModel();
                     CustomerDto customerDto = new CustomerDto();

                     customerDto.setCustId(txtId.getText());
                     customerDto.setName(txtName.getText());
                     customerDto.setAddress(txtAddress.getText());
                     customerDto.setTel(txtTel.getText());
                     customerDto.setUserName(LoginFormController.userName);
                     try {
                            boolean save = customerBO.SaveCustomer(customerDto);

                            if (save) {
                                   new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved !").show();
                            }
                     } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                     }

              } else {
                     new Alert(Alert.AlertType.ERROR, "invalid Customer details !").show();
              }

       }

       private boolean validateCustomer() {
              String id = txtId.getText();
              boolean isCustomerIdValidate = Pattern.matches("[C][0-9]{3,}", id);
              if (!isCustomerIdValidate) {
                     new Alert(Alert.AlertType.ERROR, "Invalid Id !").show();
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

              boolean isValidate = validateCustomer();
              if (isValidate) {

                     CustomerDto customerDto = new CustomerDto();

                     customerDto.setCustId(txtId.getText());
                     customerDto.setName(txtName.getText());
                     customerDto.setAddress(txtAddress.getText());
                     customerDto.setTel(txtTel.getText());
                     try {

                            boolean update = customerBO.updateCustomer(customerDto);

                            if (update) {
                                   new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated !").show();
                            }
                     } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                     }
              } else {
                     new Alert(Alert.AlertType.ERROR, "Customer invalid,can't update!").show();
              }
       }


       @FXML
       void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
              String id = txtId.getText();

              CustomerDto search = customerBO.searchCustomer(id);

              txtName.setText(search.getName());
              txtAddress.setText(search.getAddress());
              txtTel.setText(search.getTel());
       }




       private void setCellValueFactory() {
              colCusId.setCellValueFactory(new PropertyValueFactory<>("CusId"));
              colName.setCellValueFactory(new PropertyValueFactory<>("name"));
              colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
              colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
       }

       private void getAllCustomer() throws ClassNotFoundException {
              //var model = new CustomerModel();

              ObservableList<Customertm> obList = FXCollections.observableArrayList();

              try {
                     List<CustomerDto> dtoList = customerBO.getAllCustomers();

                     for(CustomerDto dto : dtoList) {
                            obList.add(
                                    new Customertm(
                                            dto.getCustId(),
                                            dto.getName(),
                                            dto.getAddress(),
                                            dto.getTel()
                                    )
                            );
                     }

                     tblCustomer.setItems(obList);
              } catch (SQLException e) {
                     throw new RuntimeException(e);
              }
       }
}

