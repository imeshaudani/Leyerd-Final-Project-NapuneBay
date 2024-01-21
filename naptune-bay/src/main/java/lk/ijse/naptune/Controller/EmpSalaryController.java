package lk.ijse.naptune.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.naptune.dto.EmpSalaryDto;
import lk.ijse.naptune.dto.tm.EmpSalaryTm;
import lk.ijse.naptune.model.EmpSalaryModel;

import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EmpSalaryController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtid;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableView<EmpSalaryTm> tblAttendence;

    EmpSalaryModel empSalaryModel = new EmpSalaryModel();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        try {
            MainFormController.getInstance().bodypane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/employeeForm.fxml"));
            Parent root = loader.load();
            MainFormController.getInstance().bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isValidate = validateCustomer();
        if (isValidate) {
            EmpSalaryModel empSalaryModel = new EmpSalaryModel();
            EmpSalaryDto empSalaryDto = new EmpSalaryDto();

            empSalaryDto.setSalaryId(txtid.getText());
            empSalaryDto.setAmount(txtAmount.getText());
            empSalaryDto.setMonth(txtMonth.getText());

            try {
                boolean save = empSalaryModel.save(empSalaryDto);

                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Salary Saved !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "invalid Salary details !").show();
        }
    }

    private boolean validateCustomer() {
        String id = txtid.getText();
        boolean isEmpSalaryIdValidate = Pattern.matches("[S][0-9]{3,}", id);
        if (!isEmpSalaryIdValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id !").show();
            return false;
        }


        String month = txtMonth.getText();
        boolean isEmpMonthValidate = Pattern.matches("[A-za-z]{3,}", month);
        if (!isEmpMonthValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid Month !").show();
            return false;
        }

        return true;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        EmpSalaryModel model = new EmpSalaryModel();
        boolean delete = model.delete(txtid.getText());

        if (delete) {
            new Alert(Alert.AlertType.CONFIRMATION, "SalaryId Deleted !").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();

        EmpSalaryDto search = empSalaryModel.search(id);

        txtid.setText(search.getSalaryId());
        txtAmount.setText(search.getAmount());
        txtMonth.setText(search.getMonth());
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtid.clear();
        txtAmount.clear();
        txtMonth.clear();

    }



   public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValidate = validateCustomer();
        if (isValidate) {

            EmpSalaryDto empSalaryDto = new EmpSalaryDto();

            empSalaryDto.setSalaryId(txtid.getText());
            empSalaryDto.setAmount(txtAmount.getText());
            empSalaryDto.setMonth(txtMonth.getText());
            try {

                boolean update =EmpSalaryModel.update(empSalaryDto);

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

    private void update(EmpSalaryDto empSalaryDto) {

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
    }

    private void loadAllCustomers() {
        var model = new EmpSalaryModel();

        ObservableList<EmpSalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<EmpSalaryDto> dtoList = model.getAllCustomers();

            for(EmpSalaryDto dto : dtoList) {
                obList.add(
                        new EmpSalaryTm(
                                dto.getSalaryId(),
                                dto.getAmount(),
                                dto.getMonth()
                        )
                );
            }

            tblAttendence.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

