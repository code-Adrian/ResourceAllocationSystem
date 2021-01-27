package Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    TextField Username;
    @FXML
    PasswordField Password;
    //This is the Login for the Admin Account ============================= FOR TEACHER ========================
     String username = "Admin";
     String password = "Admin";



    public void GoAdminPanel(ActionEvent event) throws IOException {

        if(Username.getText().contentEquals(username) && Password.getText().contentEquals(password)){
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        }else{
            //Validation of Admin
            Username.setText("Username Incorrect");
            Password.setText("Password Incorrect");
            System.out.println("UserName or Password Incorrect");

        }


    }

    public void MainScreenReturn(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
    @FXML
    ImageView iv;
    private void Image() {
        String Location = getClass().getResource("/Img/pitch.jpg").toString();
        javafx.scene.image.Image image = new Image(Location);
        iv.setImage(image);
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Automatic Username and Password fill. So the teacher doesn't have to look for credentials.
        Username.setText("Admin");
        Password.setText("Admin");

Image();
    }
}
