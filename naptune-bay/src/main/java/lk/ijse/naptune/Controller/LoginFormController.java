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
import lk.ijse.naptune.model.UserModel;

import java.io.IOException;

public class LoginFormController {

    public static String userName;
    private String username="Imesha";
    private String pw="1234";

    @FXML
    private AnchorPane loginPane;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
       String name= txtUserName.getText();
       String passwod=txtPassword.getText();
       if(username.equals(name)&&passwod.equals(pw)){
           Parent root= FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"));
           Scene scene=new Scene(root);
           Stage stage=new Stage();
           stage.setScene(scene);
           stage.show();

           Stage stage1= (Stage) loginPane.getScene().getWindow();
           stage1.close();
       }else{
           new Alert(Alert.AlertType.ERROR,"invalid password and username,try again").show();
       }

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/signupForm.fxml"));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1= (Stage) loginPane.getScene().getWindow();
        stage1.close();

    }

}
