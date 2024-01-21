package lk.ijse.naptune.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.naptune.dto.SupOrderDto;
import lk.ijse.naptune.dto.tm.SupItemtm;
import lk.ijse.naptune.model.SupOrderModel;
import lk.ijse.naptune.model.SuppliersModel;
import lk.ijse.naptune.model.transaction.PlaceSupplierOrderTransactionModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SupplierOrderFormController {
    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupItemtm> tblSupItem;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTime;

    @FXML
    private ComboBox<String> cmbSupId;

    PlaceSupplierOrderTransactionModel placeSupplierOrderTransactionModel = new PlaceSupplierOrderTransactionModel();
    SupOrderModel supItemModel = new SupOrderModel();
    SuppliersModel suppliersModel = new SuppliersModel();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        setDate();
        setTime();
        try {
            setDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDataInComboBox() throws SQLException {
        ArrayList<String> supId = suppliersModel.getAllSuppliersId();
        cmbSupId.getItems().addAll(supId);
    }

    private void setDate(){
        String date = String.valueOf(LocalDate.now());
        txtDate.setText(date);
    }

    private void setTime(){
        String time = String.valueOf(LocalTime.now());
        txtTime.setText(time);
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtDate.clear();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        SupOrderDto search = supItemModel.search(id);

        txtName.setText(search.getName());
        txtDate.setText(search.getDate());

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        SupOrderModel model = new SupOrderModel();
        boolean delete = model.delete(txtId.getText());

        if (delete) {
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted !").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            SupOrderModel supOrderModel = new SupOrderModel();
            SupOrderDto supOrderDto = new SupOrderDto();


            supOrderDto.setSupItemId(txtId.getText());
            supOrderDto.setName(txtName.getText());
            supOrderDto.setDate(txtDate.getText());
            supOrderDto.setTime(txtTime.getText());
            supOrderDto.setSupId(cmbSupId.getSelectionModel().getSelectedItem());
            //supItemDto.setOrderList();


            try {
                boolean save = supOrderModel.save(supOrderDto);

                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Saved !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "").show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "invalid Item details !").show();
        }

    }

    private boolean validateCustomer() {
        String id = txtId.getText();
        boolean isCustomerIdValidate = Pattern.matches("[I][0-9]{3,}", id);
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

        return true;
    }
    
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            SupOrderDto supItemDto = new SupOrderDto();

            supItemDto.setSupItemId(txtId.getText());
            supItemDto.setName(txtName.getText());
            supItemDto.setDate(txtDate.getText());
            supItemDto.setTime(txtTime.getText());

            try {

                boolean update = supItemModel.update(supItemDto);

                if (update) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Item Updated !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Item invalid,can't update!").show();
        }
    }



    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/suppliersForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("supItemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void loadAllCustomers() {
        var model = new SupOrderModel();

        ObservableList<SupItemtm> obList = FXCollections.observableArrayList();

        try {
            List<SupOrderDto> dtoList = model.getAllCustomers();

            for(SupOrderDto dto : dtoList) {
                obList.add(
                        new SupItemtm(
                                dto.getSupItemId(),
                                dto.getName(),
                                dto.getDate(),
                                dto.getTime()
                        )
                );
            }

            tblSupItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
