package lk.ijse.naptune.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.naptune.bo.BOFactory;
import lk.ijse.naptune.bo.Custom.OrderBO;
import lk.ijse.naptune.bo.Custom.SuppliersBO;
import lk.ijse.naptune.dto.CustomerDto;
import lk.ijse.naptune.dto.SuppliersDto;
import lk.ijse.naptune.dto.tm.Supplierstm;
import lk.ijse.naptune.model.CustomerModel;
import lk.ijse.naptune.model.SuppliersModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class  SuppliersFormController {
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Supplierstm> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtId;


    @FXML
    private TextField txtTel;

    SuppliersModel suppliersModel = new SuppliersModel();

    SuppliersBO suppliersBO = (SuppliersBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.SUPPLIERS);


    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        getAllCustomers();
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
            boolean isDeleted = suppliersBO.delete(id);

            if(isDeleted){
                tblSupplier.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Suppliers deleted! ").show();
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

            //SuppliersModel suppliersModel = new SuppliersModel();
            SuppliersDto suppliersDto = new SuppliersDto();

            suppliersDto.setId(txtId.getText());
            suppliersDto.setName(txtName.getText());
            suppliersDto.setAddress(txtAddress.getText());
            suppliersDto.setTel(txtTel.getText());

            try {
                boolean save = suppliersBO.save(suppliersDto);

                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Suppliers Saved !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "invalid Suppliers details !").show();
        }
    }

    private boolean validateCustomer() {
        String id = txtId.getText();
        boolean isCustomerIdValidate = Pattern.matches("[S][0-9]{3,}", id);
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
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        SuppliersDto search = suppliersBO.search(id);

        txtName.setText(search.getName());
        txtAddress.setText(search.getAddress());
        txtTel.setText(search.getTel());
    }

    @FXML
    void btnSupItemOnAction(ActionEvent event) throws IOException {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/supplierItemForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrder(ActionEvent event)  throws IOException {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/placeSupOrderForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            SuppliersDto suppliersDto = new SuppliersDto();

            suppliersDto.setId(txtId.getText());
            suppliersDto.setName(txtName.getText());
            suppliersDto.setAddress(txtAddress.getText());
            suppliersDto.setTel(txtTel.getText());
            try {

                boolean update = suppliersBO.update(suppliersDto);

                if (update) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Suppliers Updated !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Suppliers invalid,can't update!").show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("supId"));
        colName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("tel"));

    }

    private void getAllCustomers() throws ClassNotFoundException {
        //var model = new SuppliersModel();

        ObservableList<Supplierstm> obList = FXCollections.observableArrayList();

        try {
            List<SuppliersDto> dtoList = suppliersBO.getAllCustomers();

            for (SuppliersDto dto : dtoList) {
                obList.add(
                        new Supplierstm(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getTel()
                        )
                );
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
