package lk.ijse.naptune.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {

    @FXML
    public Pane bodypane;

    @FXML
    public Pane signUpPane;

    @FXML
    private AnchorPane root;

    private static MainFormController controller;

    public MainFormController() {
        controller = this;
    }

    public static MainFormController getInstance(){
        return controller;
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/customerForm.fxml"));
            Parent root = loader.load();
            bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/dashboardForm.fxml"));
            Parent root = loader.load();
            bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/employeeForm.fxml"));
            Parent root = loader.load();
            bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/ordersForm.fxml"));
            Parent root = loader.load();
            bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/paymentForm.fxml"));
            Parent root = loader.load();
            bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSignOutOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/signupForm.fxml"));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1= (Stage) signUpPane .getScene().getWindow();
        stage1.close();
    }

    @FXML
    void btnStockOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/stockForm.fxml"));
            Parent root = loader.load();
            bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainFormController.class.getResource("/view/suppliersForm.fxml"));
            Parent root = loader.load();
            bodypane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
