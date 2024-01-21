package lk.ijse.naptune.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.naptune.dto.MenuDto;
import lk.ijse.naptune.dto.tm.MenuTm;
import lk.ijse.naptune.model.MenuModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class MenuFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableView<MenuTm> tblMenuItem;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;
    MenuModel menuModel = new MenuModel();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/ordersForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        MenuModel model = new MenuModel();
        boolean delete = model.delete(txtId.getText());

        if (delete) {
            new Alert(Alert.AlertType.CONFIRMATION, "Menu Deleted !").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            MenuModel menuModel = new MenuModel();
            MenuDto menuDto = new MenuDto();

            menuDto.setItemCode(txtId.getText());
            menuDto.setName(txtName.getText());
            menuDto.setPrice(txtPrice.getText());

            try {
                boolean save = menuModel.save(menuDto);

                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Menu Saved !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "invalid Menu details !").show();
        }

    }

    private boolean validateCustomer() {
        String id = txtId.getText();
        boolean isCustomerIdValidate = Pattern.matches("[M][0-9]{3,}", id);
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
    void btnSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        MenuDto search = menuModel.search(id);

        txtName.setText(search.getName());
        txtPrice.setText(search.getPrice());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            MenuDto menuDto = new MenuDto();

            menuDto.setItemCode(txtId.getText());
            menuDto.setName(txtId.getText());
            menuDto.setPrice(txtPrice.getText());

            try {
                boolean update = menuModel.update(menuDto);

                if (update) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Menu Updated !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Menu invalid,can't update!").show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    private void loadAllCustomers() {
        var model = new MenuModel();

        ObservableList<MenuTm> obList = FXCollections.observableArrayList();

        try {
            List<MenuDto> dtoList = model.getAllCustomers();

            for(MenuDto dto : dtoList) {
                obList.add(
                        new MenuTm(
                                dto.getItemCode(),
                                dto.getName(),
                                dto.getPrice()
                        )
                );
            }

            tblMenuItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
