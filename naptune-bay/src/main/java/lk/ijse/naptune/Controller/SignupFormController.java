package lk.ijse.naptune.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupFormController {

    private String username="Imesha";
    private String pw="1234";
    private String confirm="1234";

    @FXML
    public AnchorPane signUpPane;

    @FXML
    private PasswordField txtConfirmPw;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1= (Stage) signUpPane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        String name= txtUserName.getText();
        String password=txtPassword.getText();
        String conform=txtConfirmPw.getText();
        if(username.equals(name)&&password.equals(pw)&&conform.equals(conform)){
            Parent root= FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"));
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            Stage stage1= (Stage) signUpPane.getScene().getWindow();
            stage1.close();
        }else{
            new Alert(Alert.AlertType.ERROR,"invalid password and username,try again").show();
        }


    }
}
