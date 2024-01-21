package lk.ijse.naptune.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.naptune.bo.BOFactory;
import lk.ijse.naptune.bo.Custom.OrderBO;
import lk.ijse.naptune.bo.Custom.StockBO;
import lk.ijse.naptune.dto.OrderDto;
import lk.ijse.naptune.dto.StockDto;
import lk.ijse.naptune.dto.SuppliersDto;
import lk.ijse.naptune.dto.tm.Stocktm;
import lk.ijse.naptune.model.OrderModel;
import lk.ijse.naptune.model.StockModel;
import lk.ijse.naptune.model.SuppliersModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class StockFormController {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Stocktm> tblStockManage;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    private final StockModel stockModel = new StockModel();

    StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.STOCK);


    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        getAllCustomers();
        //setListener();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        txtQty.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();


        try{
            boolean isDeleted = stockBO.delete(id);

            if(isDeleted){
                tblStockManage.refresh();
                new Alert(Alert.AlertType.CONFIRMATION,"Stock deleted! ").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateStock();
        if (isValidate) {

               // StockModel stockModel = new StockModel();
                StockDto stockDto = new StockDto();

                stockDto.setCode(txtId.getText());
                stockDto.setDescription(txtName.getText());
                stockDto.setUnitPrice(txtPrice.getText());
                stockDto.setQtyOnHand(txtQty.getText());

                try {
                    boolean save = stockBO.Save(stockDto);

                    if (save) {
                        new Alert(Alert.AlertType.CONFIRMATION, "stock Saved !").show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

        } else {
                new Alert(Alert.AlertType.ERROR, "invalid stock details !").show();
        }
    }
    private boolean validateStock() {
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

        return true;
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtQty.setText("");
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isValidate = validateStock();
        if (isValidate) {

            StockDto stockDto = new StockDto();

            stockDto.setCode(txtId.getText());
            stockDto.setDescription(txtName.getText());
            stockDto.setUnitPrice(txtPrice.getText());
            stockDto.setQtyOnHand(txtQty.getText());
            try {

                boolean update = stockBO.update(stockDto);

                if (update) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Stock Updated !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Stock invalid,can't update!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        StockDto search = stockBO.search(id);

        txtName.setText(search.getDescription());
        txtPrice.setText(String.valueOf(search.getUnitPrice()));
        txtQty.setText(String.valueOf(search.getQtyOnHand()));
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
    }

    private void getAllCustomers() throws ClassNotFoundException {
        try {
            List<StockDto> dtoList = stockBO.getAllCustomers();

            ObservableList<Stocktm> obList = FXCollections.observableArrayList();

            for(StockDto dto : dtoList) {
                Button btn = new Button("remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        int selectedIndex = tblStockManage.getSelectionModel().getSelectedIndex();
                        String code = (String) colId.getCellData(selectedIndex);

                        deleteStock(code);   //delete item from the database

                        obList.remove(selectedIndex);   //delete item from the JFX-Table
                        tblStockManage.refresh();
                    }
                });

                var tm = new Stocktm(
                        dto.getCode(),
                        dto.getDescription(),
                        dto.getUnitPrice(),
                        dto.getQtyOnHand(),
                        btn
                );
                obList.add(tm);
            }
            tblStockManage.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteStock(String code) {
        try {
            boolean isDeleted = stockModel.deleteStock(code);
            if(isDeleted)
                new Alert(Alert.AlertType.CONFIRMATION, "Stock deleted!").show();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }
    }

    /*private void setListener() {
        tblStockManage.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    var dto = new StockDto(
                            newValue.getCode(),
                            newValue.getDescription(),
                            newValue.getUnitPrice(),
                            newValue.getQtyOnHand()

                    );
                    setFields(dto);
                });
    }

    private void setFields(StockDto dto) {
        txtId.setText(dto.getCode());
        txtName.setText(dto.getDescription());
        txtPrice.setText(String.valueOf(dto.getUnitPrice()));
        txtQty.setText(String.valueOf(dto.getQtyOnHand()));
    }*/
}
