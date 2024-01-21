package lk.ijse.naptune.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.naptune.dto.PlaceOderDto;
import lk.ijse.naptune.dto.StockDto;
import lk.ijse.naptune.dto.SupOrderDto;
import lk.ijse.naptune.dto.SuppliersDto;
import lk.ijse.naptune.dto.tm.CartTm;
import lk.ijse.naptune.model.StockModel;
import lk.ijse.naptune.model.SupOrderModel;
import lk.ijse.naptune.model.SuppliersModel;
import lk.ijse.naptune.model.transaction.PlaceSupplierOrderTransactionModel;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceSupOrderFormController {

    SupOrderModel supOrderModel = new SupOrderModel();
    StockModel stockModel = new StockModel();
    SuppliersModel suppliersModel = new SuppliersModel();
    PlaceSupplierOrderTransactionModel placeSupplierOrderTransactionModel = new PlaceSupplierOrderTransactionModel();
    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();

    @FXML
    private Button btnAddToCart;

    @FXML
    private ComboBox<String> cmbSupId;

    @FXML
    private ComboBox<String> cmbStockId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStockId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CartTm> tblOrderCart;

    @FXML
    private TextField txtSupName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtNetTotal;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;
    private String supItemId;
    private String supId;



    public void initialize() {
        setCellValueFactory();
        generateNextOrderId();
        setDate();
        loadSuppliersIds();
        loadStockCodes();
    }

    private void setCellValueFactory() {
        colStockId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextOrderId() {
        try {
            String orderId = supOrderModel.generateNextOrderId();
            txtOrderId.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStockCodes() throws RuntimeException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<StockDto> itemList = stockModel.loadAllStock();

            for (StockDto itemDto : itemList) {
                obList.add(itemDto.getCode());
            }

            cmbStockId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSuppliersIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SuppliersDto> cusList = suppliersModel.loadAllSuppliers();

            for (SuppliersDto dto : cusList) {
                obList.add(dto.getId());
            }
            cmbSupId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        txtOrderDate.setText(date);
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbStockId.getValue();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = qty * unitPrice;
        Button btn = new Button("remove");
        btn.setCursor(Cursor.HAND);

        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int index = tblOrderCart.getSelectionModel().getSelectedIndex();
                obList.remove(index);
                tblOrderCart.refresh();

                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colStockId.getCellData(i))) {
                qty += (int) colQty.getCellData(i);
                total = qty * unitPrice;

                obList.get(i).setQty(qty);
                obList.get(i).setTot(total);

                tblOrderCart.refresh();
                calculateNetTotal();
                return;
            }
        }

        obList.add(new CartTm(
                code,
                description,
                qty,
                unitPrice,
                total,
                btn
        ));

        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.clear();
    }

    private void calculateNetTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }

        txtNetTotal.setText(String.valueOf(total));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/suppliersForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {
        String supItemId = txtOrderId.getText();
        String supId = cmbSupId.getValue();
        LocalDate date = LocalDate.parse(txtOrderDate.getText());

        List<CartTm> tmList = new ArrayList<>();

        for (CartTm cartTm : obList) {
            tmList.add(cartTm);
        }

        var placeOrderDto = new PlaceOderDto(
                supItemId,
                supId,
                date,
                tmList
        );

        try {
            boolean isSuccess = placeSupplierOrderTransactionModel.placeSupplierOrder(placeOrderDto);
            if(isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "order completed!").show();
            }else {
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbSupOnAction(ActionEvent event) throws SQLException {
        String id = cmbSupId.getValue();
        SuppliersDto dto = suppliersModel.searchSupplier(id);

        txtSupName.setText(dto.getName());
    }

    @FXML
    void cmbStockOnAction(ActionEvent event) {
        String code = cmbStockId.getValue();

        txtQty.requestFocus();

        try {
            StockDto dto = stockModel.searchStock(code);

            txtDescription.setText(dto.getDescription());
            txtUnitPrice.setText(dto.getUnitPrice());
            txtQtyOnHand.setText(dto.getQtyOnHand());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {btnAddToCartOnAction(event); }

}