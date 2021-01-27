package Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.lang.*;


public class Controller implements Initializable {
    private UserFunction users = new UserFunction();

@FXML
Pane pane;
@FXML
ImageView iv;

    @FXML
    private void Image() {
String Location = getClass().getResource("/Img/pitch.jpg").toString();
        Image image = new Image(Location);
        iv.setImage(image);
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            users.load();
        } catch (Exception e) {
           System.out.println("User DataBase hasn't been created or is Corrupt");
        }
        Image();

    }

    public void MainScreenReturn(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    public void GoUserLogin(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ULogin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    public void GoAdminLogin(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }


    public void GoUserPanel(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    public void GoRegister(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
// This gives the user the ability to register and add himself into the system
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField User_Id;
    @FXML
    private Label ErrorChecker;

    public void RegisterUser(){
try {
    //Validation
    if (FirstName.getText().length() > 12 || FirstName.getText().length() < 2) {
        ErrorChecker.setTextFill(Color.web("#FF0000", 1));
        ErrorChecker.setText("Error");
        FirstName.setText("Name too short or too long");
    } else if (LastName.getText().length() > 12 || LastName.getText().length() < 2) {
        ErrorChecker.setTextFill(Color.web("#FF0000", 1));
        ErrorChecker.setText("Error");
        LastName.setText("Last Name too short or too long");
    }else if (PhoneNumber.getText().length() < 10 ) {
        ErrorChecker.setTextFill(Color.web("#FF0000", 1));
        ErrorChecker.setText("Error");
        PhoneNumber.setText("Mobile number must be 10 or above");
    }else if(!Email.getText().contains("@") || !Email.getText().contains(".") || Email.getText().length() < 4 || users.checkIfEmail_IdExists(Email.getText())){
        ErrorChecker.setTextFill(Color.web("#FF0000", 1));
        ErrorChecker.setText("Error");
        Email.setText("Email must contain '.' and '@' ");

    }else if(Password.getText().length() < 4 || Password.getText().equals(Password.getText().toLowerCase())){
        ErrorChecker.setTextFill(Color.web("#FF0000", 1));
        ErrorChecker.setText("Error");
        Password.setText("Password must contain 1 uppercase");
    }else if(User_Id.getText().length() > 3 || users.checkIfUser_IdExists(Integer.parseInt(User_Id.getText())) == true){
        ErrorChecker.setTextFill(Color.web("#FF0000", 1));
        ErrorChecker.setText("Error");
        User_Id.setText("User id already taken");

    }
    else{
        ErrorChecker.setTextFill(Color.web("#00FF00", 1));
        ErrorChecker.setText("Success!");
        Thread.sleep(300);
        //Adding


        User u = new User(FirstName.getText(),LastName.getText(),Integer.parseInt(PhoneNumber.getText()),Email.getText(),Password.getText(),Integer.parseInt(User_Id.getText()));
        users.addUser(u);
        users.save();
    }

}catch(Exception e){
    System.out.println(e.toString());
}

    }




}