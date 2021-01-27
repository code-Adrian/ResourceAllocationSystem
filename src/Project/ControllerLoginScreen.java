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

public class ControllerLoginScreen implements Initializable {

    private UserFunction users = new UserFunction();

    //Constructor, get access from other classes to retrieve variables, eg. User_id / passed from here.
 public ControllerLoginScreen(){

    }

    @FXML
    ImageView iv;

    @FXML
    private void Image() {
        String Location = getClass().getResource("/Img/pitch.jpg").toString();
        Image image = new Image(Location);
        iv.setImage(image);
    }


    /*
    This Variable is going to get passed to UserPanel as a unique identifier.
    It will be used to organise and view reservations and facilities that share the same identifier
     */
    private static String User_Id;


    @FXML
    TextField LoginEmail;
    @FXML
    PasswordField Password;

    public void GoUserPanel(ActionEvent event) throws IOException {
        //Validation of Login
        if(LoginEmail.getText().length() < 3 | Password.getText().length() < 3 | LoginEmail.getText().isEmpty() | LoginEmail.getText().isEmpty()){
            System.out.println("Access Denied, Password too short");
        }else if(LoginEmail.getText().contentEquals(users.getUserByEmail(LoginEmail.getText())) && Password.getText().contentEquals((users.getUserByPassword(Password.getText())))){
            System.out.println("Access Granted");
            //Passing user identifier to UserPanel using getter.
            User_Id = users.getUserByIdentifierFromEmail(LoginEmail.getText());
            System.out.println("Logged in with User_ID: "+users.getUserByIdentifierFromEmail(LoginEmail.getText()));
//Privileged Access to User Login
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        }else{
            System.out.println("Access Denied, Bad Login\n");
            //If Bad login occurs, Password cannot be identical to another account. This is a bug



System.out.println("Email entered: "+LoginEmail.getText());

System.out.println("Password entered: "+Password.getText());


        }

    }

    public void MainScreenReturn(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
    public String getUser_Id() {
        return User_Id;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            users.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image();
    }
}
