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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.spec.ECField;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminAccountController implements Initializable {

    ControllerLoginScreen userID = new ControllerLoginScreen();
    private UserFunction users = new UserFunction();
    private FacilityFunction facilitys = new FacilityFunction();
    private ReservationFunction rerservations = new ReservationFunction();



@FXML
TextField ID;
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
try {
    User user;
    user = users.getUser(users.getUserIndexByID(Integer.parseInt(ID.getText())));
    user.setEmail(Email.getText());
    user.setPassword(Password.getText());
    user.setMobile(Integer.parseInt(Mobile.getText()));
    users.save();
    ErrorChecker.setTextFill(Color.web("#00FF00", 1));
    ErrorChecker.setText("Success in updating!");
    System.out.println(users.PrintAllUsers());
}catch(Exception update){
    ErrorChecker.setTextFill(Color.web("#FF0000", 1));
    ErrorChecker.setText("Invalid ID");
}
        }
    }











    public void BacktoAdminPanel(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }




    @FXML
    Label TotalAccountNumber;

    @FXML
    TextField AccountID;

    public void DeleteAccount(ActionEvent event) throws Exception {
if(AccountID.getText().isBlank()){
    Alert error = new Alert(Alert.AlertType.ERROR);
    error.setTitle("User ID is blank");
    error.setHeaderText("Error in User_ID");
    error.setContentText("You must provide a valid User_Id in order to delete a user by that ID!");

    error.showAndWait();
}else {
  try {
      int ID = Integer.parseInt(AccountID.getText());
      int AccountID = users.getUserIndexByID(ID);

      if (users.getUserIndexByID(ID) == -1) {
          System.out.println("=====WRONG USERID=====");
      }
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Account Removal");
          alert.setHeaderText("Remove Account?");
          alert.setContentText("Are you sure you want to remove this account?");


          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK) {


              try {
                  users.removeUser(AccountID);
                  facilitys.RemoveAllFacilityByID(AccountID);
                  rerservations.RemoveAllReservationsyByID(AccountID);
              } catch (Exception e) {

                  Alert alert2 = new Alert(Alert.AlertType.ERROR);
                  alert2.setTitle("Invalid User_ID Number!");
                  alert2.setContentText("Please enter a valid user ID");
              }
              users.save();
              facilitys.save();
              rerservations.save();
              TotalAccountNumber.setText(Integer.toString(users.numberOfUsers()));

              System.out.println("Successfully Removed User by the ID of: " + AccountID);
          } else {
              alert.close();
          }

      }catch(Exception e){

      }
}
    }

    public void viewAccounts(){
        Alert accounts = new Alert(Alert.AlertType.INFORMATION);
        accounts.setTitle("View Active Accounts");
        accounts.setHeaderText("Active User Account list");
        accounts.setContentText(users.PrintAllUsers());

        accounts.show();
    }
    @FXML
    ImageView iv;
    private void Image() {
        String Location = getClass().getResource("/Img/pitch.jpg").toString();
        Image image = new Image(Location);
        iv.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            users.load();
            rerservations.load();
            facilitys.load();
        } catch (Exception e) {

        }

        try {
            TotalAccountNumber.setText(Integer.toString(users.numberOfUsers()));




        } catch (Exception e) {
            e.toString();
        }
        try {

        }catch(Exception e){
            System.out.println(e.toString());
        }

        Image();

    }
}
