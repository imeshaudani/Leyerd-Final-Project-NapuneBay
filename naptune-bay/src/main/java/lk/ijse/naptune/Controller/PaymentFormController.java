package lk.ijse.naptune.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.naptune.bo.BOFactory;
import lk.ijse.naptune.bo.Custom.OrderBO;
import lk.ijse.naptune.bo.Custom.PaymentBO;
import lk.ijse.naptune.dto.PaymentDto;
import lk.ijse.naptune.dto.tm.PaymentTm;
import lk.ijse.naptune.model.PaymentModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class PaymentFormController {

    @FXML
    private ComboBox<?> cmbId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colOId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    PaymentModel paymentModel = new PaymentModel();

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.PAYMENT);


    public void initialize() {
        setCellValueFactory();
        getAllCustomers();
        setDate();
    }

    private void setDate(){
        String date = String.valueOf(LocalDate.now());
        txtDate.setText(date);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtDate.clear();
        txtAmount.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();


        try{
            boolean isDeleted = paymentBO.delete(id);

            if(isDeleted){
                tblPayment.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Payment deleted! ").show();
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

            //PaymentModel paymentModel = new PaymentModel();
            PaymentDto paymentDto = new PaymentDto();

            paymentDto.setPayId(txtId.getText());
            paymentDto.setDate(txtDate.getText());
            paymentDto.setAmount(txtAmount.getText());

            try {
                boolean save = paymentBO.save(paymentDto);

                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment Saved !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "invalid Payment details !").show();
        }

    }

    private boolean validateCustomer() {
        String id = txtId.getText();
        boolean isCustomerIdValidate = Pattern.matches("[P][0-9]{3,}", id);
        if (!isCustomerIdValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id !").show();
            return false;
        }

        return true;
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        PaymentDto search = paymentBO.search(id);

        txtDate.setText(search.getDate());
        txtAmount.setText(search.getAmount());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            PaymentDto paymentDto = new PaymentDto();

            paymentDto.setPayId(txtId.getText());
            paymentDto.setDate(txtDate.getText());
            paymentDto.setAmount(txtAmount.getText());
            try {
                boolean update = paymentBO.update(paymentDto);

                if (update) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment invalid,can't update!").show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("payId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    private void getAllCustomers() {
        //var model = new PaymentModel();

        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDto> dtoList = paymentBO.getAllCustomers();

            for(PaymentDto dto : dtoList) {
                obList.add(
                        new PaymentTm(
                                dto.getPayId(),
                                dto.getDate(),
                                dto.getAmount()
                        )
                );
            }

            tblPayment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
