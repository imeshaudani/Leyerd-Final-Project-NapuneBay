package lk.ijse.naptune.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.naptune.dto.EmpAttendDto;
import lk.ijse.naptune.dto.tm.EmpAttendanceTm;
import lk.ijse.naptune.model.EmpAttendModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class EmpAttendanceController {

    @FXML
    private TableColumn<?, ?> colCount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmpAttendanceTm> tblAttendence;

    @FXML
    private TextField txtAttendanceId;

    @FXML
    private TextField txtCount;

    @FXML
    private TextField txtDate;


    EmpAttendModel empAttendModel = new EmpAttendModel();

    public void initialize() throws SQLException {
        setDate();
        setCellValueFactory();
        loadAllEmployee();
    }
    private void setDate(){
        String date = String.valueOf(LocalDate.now());
        txtDate.setText(date);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtAttendanceId.clear();
        txtDate.cancelEdit();
        txtCount.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        EmpAttendModel model = new EmpAttendModel();
        boolean delete = model.delete(txtAttendanceId.getText());

        if(delete) {
            new Alert(Alert.AlertType.CONFIRMATION, " Deleted !").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        boolean isValidate = validateCustomer();
        if (isValidate) {

            EmpAttendModel empAttendModel = new EmpAttendModel();
            EmpAttendDto employeeAttendanceDto = new EmpAttendDto();

            employeeAttendanceDto.setAttendanceId(txtAttendanceId.getId());
            employeeAttendanceDto.setDate(txtDate.getText());
            employeeAttendanceDto.setCount(txtCount.getText());

            try {
                boolean save = empAttendModel.save(employeeAttendanceDto);

                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Attendance Saved !").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "invalid Attendance details !").show();
        }

    }


    private boolean validateCustomer() {
        String id = txtAttendanceId.getText();
        boolean isCustomerIdValidate = Pattern.matches("[A][0-9]{3,}", id);
        if (!isCustomerIdValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id !").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtAttendanceId.getText();

        EmpAttendDto search = empAttendModel.search(id);

        txtAttendanceId.setText(search.getAttendanceId());
        txtDate.setText(search.getDate());
        txtCount.setText(search.getCount());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        EmpAttendDto employeeAttendanceDto = new EmpAttendDto();

        employeeAttendanceDto.setAttendanceId(txtAttendanceId.getText());
        employeeAttendanceDto.setDate(txtDate.getText());
        employeeAttendanceDto.setCount(txtCount.getText());


        boolean update = empAttendModel.update(employeeAttendanceDto);

        if (update) {
            new Alert(Alert.AlertType.CONFIRMATION, " Updated !").show();
        }
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

    private void setCellValueFactory() {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("attendanceId"));
        colDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("date"));
        colCount.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("count"));

    }

    private void loadAllEmployee() throws SQLException {
        var model = new EmpAttendModel();

        ObservableList<EmpAttendanceTm> obList = FXCollections.observableArrayList();

        List<EmpAttendDto> dtoList = model.loadAllEmployee();

        for(EmpAttendDto dto : dtoList) {
            obList.add(new EmpAttendanceTm(
                    dto.getAttendanceId(),
                    dto.getDate(),
                    dto.getCount()
            ));
        }
        tblAttendence.setItems(obList);
    }


}
