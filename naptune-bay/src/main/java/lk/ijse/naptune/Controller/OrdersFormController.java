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
import lk.ijse.naptune.bo.Custom.EmployeeBO;
import lk.ijse.naptune.bo.Custom.OrderBO;
import lk.ijse.naptune.dto.CustomerDto;
import lk.ijse.naptune.dto.OrderDto;
import lk.ijse.naptune.dto.tm.Ordertm;
import lk.ijse.naptune.model.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class OrdersFormController {
    @FXML
    private TableColumn<?, ?> colCust_id;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDetails;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colOrder_Id;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Ordertm> tblOrder;

    @FXML
    private ComboBox<?> txtCust_id;

    @FXML
    private TextField txtdate;

    @FXML
    private TextField txtDetails;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtId;

    OrderModel orderModel = new OrderModel();

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.ORDER);


    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        getAllCustomers();
        setDate();
    }

    private void setDate(){
        String date = String.valueOf(LocalDate.now());
        txtdate.setText(date);
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtdate.clear();
        txtDetails.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();


        try{
            boolean isDeleted = orderBO.delete(id);

            if(isDeleted){
                tblOrder.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Order deleted! ").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnItemMenuOnAction(ActionEvent event) throws IOException {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/menuForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            //OrderModel orderModel = new OrderModel();
            OrderDto orderDto = new OrderDto();

            orderDto.setOrderId(txtId.getText());
            orderDto.setName(txtName.getText());
            orderDto.setDate(txtdate.getText());
            orderDto.setDetails(txtDetails.getText());

            try {
                boolean save = orderBO.save(orderDto);

                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Saved !").show();
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
        boolean isCustomerIdValidate = Pattern.matches("[O][0-9]{3,}", id);
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


        String tel = txtDetails.getText();
        boolean isCustomerDetailsValidate = Pattern.matches("[A-za-z]{3,}", tel);
        if (!isCustomerDetailsValidate) {
            new Alert(Alert.AlertType.ERROR, "Inavlid Details !").show();
            return false;
        }

        return true;
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            OrderDto orderDto = new OrderDto();

            orderDto.setOrderId(txtId.getText());
            orderDto.setName(txtName.getText());
            orderDto.setDate(txtdate.getText());
            orderDto.setDetails(txtDetails.getText());
            try {

                boolean update = orderBO.update(orderDto);

                if (update) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Updated !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Order invalid,can't update!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        OrderDto search = orderBO.search(id);

        txtName.setText(search.getName());
        txtdate.setText(search.getDate());
        txtDetails.setText(search.getDetails());
    }

    private void setCellValueFactory() {
        colOrder_Id.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("orderId"));
        colName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("date"));
        colDetails.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("details"));
    }

    private void getAllCustomers() throws ClassNotFoundException {
        //var model = new OrderModel();

        ObservableList<Ordertm> obList = FXCollections.observableArrayList();

        try {
            List<OrderDto> dtoList = orderBO.getAllCustomers();

            for(OrderDto dto : dtoList) {
                obList.add(
                        new Ordertm(
                                dto.getOrderId(),
                                dto.getName(),
                                dto.getDate(),
                                dto.getDetails()
                        )
                );
            }

            tblOrder.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

