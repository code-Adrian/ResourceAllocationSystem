package Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {
    ControllerLoginScreen userID = new ControllerLoginScreen();
    private UserFunction users = new UserFunction();
    private FacilityFunction facilitys = new FacilityFunction();
    private ReservationFunction rerservations = new ReservationFunction();
    int ID = Integer.parseInt(userID.getUser_Id());
    @FXML
    TextField Email;
    @FXML
    PasswordField Password;
    @FXML
    TextField Mobile;
    @FXML
    Label ErrorChecker;

    public void UpdateAccount() throws Exception {
        if(!Email.getText().contains("@") || !Email.getText().contains(".") || Email.getText().length() < 4 || users.checkIfEmail_IdExists(Email.getText())){
            ErrorChecker.setTextFill(Color.web("#FF0000", 1));
            ErrorChecker.setText("Error");
            Email.setText("Email already exists or invalid format ");

        }else if(users.checkIfMobileExists(Integer.parseInt(Mobile.getText())) == true || Mobile.getText().length() > 11 || Mobile.getText().length() < 7){
            System.out.println(users.checkIfMobileExists(Integer.parseInt(Mobile.getText())));
            System.out.println(Mobile.getText());
            ErrorChecker.setTextFill(Color.web("#FF0000", 1));
            ErrorChecker.setText("Error");
            Mobile.setText("Mobile already exists or is not valid format");

        }else if(Email.getText().isEmpty() || Mobile.getText().isEmpty() || Password.getText().isEmpty()){
            ErrorChecker.setTextFill(Color.web("#FF0000", 1));
            ErrorChecker.setText("Password empty or too short");
        }else {

            User user;
            user = users.getUser(users.getUserIndexByID(ID));
            user.setEmail(Email.getText());
            user.setPassword(Password.getText());
            user.setMobile(Integer.parseInt(Mobile.getText()));
            users.save();
            ErrorChecker.setTextFill(Color.web("#00FF00", 1));
            ErrorChecker.setText("Success in updating!");
        }
    }

public void DeleteAccount(ActionEvent event) throws Exception {
        int AccountID = users.getUserIndexByID(ID);
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Account Removal");
    alert.setHeaderText("Remove Account?");
    alert.setContentText("Are you sure you want to remove this account?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
       users.removeUser(AccountID);
facilitys.RemoveAllFacilityByID(ID);
rerservations.RemoveAllReservationsyByID(ID);
users.save();
facilitys.save();
rerservations.save();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    } else {
        alert.close();
    }

}

    @FXML
    private Label label;

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
    public void initialize(URL location, ResourceBundle resources) {
        try {
            users.load();
            facilitys.load();
            rerservations.load();
        } catch (Exception e) {
        }

        String USERID = userID.getUser_Id();
        label.setText(USERID);
        Image();
    }

    public void BacktoUserPanel(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
}
