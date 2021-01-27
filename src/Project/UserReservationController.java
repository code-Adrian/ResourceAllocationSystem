package Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;         //Class used in this example for the Date
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserReservationController implements Initializable {
    ControllerLoginScreen userID = new ControllerLoginScreen();
    private UserFunction users = new UserFunction();
    private FacilityFunction facilitys = new FacilityFunction();
    private ReservationFunction rerservations = new ReservationFunction();

    ObservableList<String> FacilityChoose = FXCollections.observableArrayList("Soccer Pitch", "Soccer Indoor", "Footbal Pitch","Basketball Indoor");
    ObservableList<String> DurationChoose = FXCollections.observableArrayList("1 Hour","2 Hours","3 Hour","4 Hours","5 Hours","6 Hours");
    @FXML
   private ChoiceBox FacilityChoice;
    @FXML
    private ChoiceBox DurationChoice;
    @FXML
    private Label label;
@FXML
DatePicker datepicker;


private int UserID;



@FXML
Label ErrorChecker;

    public void loadData(){
        
        FacilityChoice.setItems(FacilityChoose);
        FacilityChoice.setValue("Soccer Pitch");

        DurationChoice.setItems(DurationChoose);
        DurationChoice.setValue("1 Hour");

    }
    public void CreateReservation() throws Exception {
        int ID = Integer.parseInt(userID.getUser_Id());
        LocalDate date = datepicker.getValue();
        String CurrentDate = date.getDayOfMonth()+""+date.getMonthValue()+""+date.getYear();
        String JustdayandMonth = date.getDayOfMonth()+""+date.getMonthValue()+"2020";
        int validationdate = Integer.parseInt(CurrentDate);
        int validationdateYear = Integer.parseInt(JustdayandMonth);
        int FacilityID = Integer.parseInt(CurrentDate);

        if(rerservations.getReservationAmountsByID(UserID) > 1 || facilitys.getFacilityAmountsByID(UserID) > 1){
System.out.println("You can only make 2 Reservations");
ErrorChecker.setTextFill(Color.web("#FF0000", 1));
ErrorChecker.setText("You can only make 2 Reservations");


        }else if(validationdate < validationdateYear || CurrentDate.isEmpty()){
            System.out.println("Invalid Date");
            ErrorChecker.setTextFill(Color.web("#FF0000", 1));
            ErrorChecker.setText("Invalid Date");


        } else if (rerservations.checkIfFacilityIDExists(FacilityID) == true|| facilitys.checkIfFacilityIDExists(FacilityID) == true){
            ErrorChecker.setTextFill(Color.web("#FF0000", 1));
            ErrorChecker.setText("That date has been already taken");
            System.out.println("Date has been already taken");
        }
            else {


            //Convert String to Int for object

            //Creating facility

            Facility f = new Facility(FacilityID, FacilityChoice.getSelectionModel().getSelectedItem().toString(),ID);
            facilitys.addFacility(f);
            facilitys.save();

//Create Reservation

            String Date = date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear();
            char Duration = DurationChoice.getSelectionModel().getSelectedItem().toString().charAt(0);
            Reservation r = new Reservation(FacilityID, ID, Date, Integer.parseInt(String.valueOf(Duration)), ID);
            rerservations.addReservation(r);
            rerservations.save();
            ErrorChecker.setTextFill(Color.web("#00FF00", 1));
            ErrorChecker.setText("Reservation created!");
            System.out.println("Successfuly created Facility and Reservation files");
        }
    }
    //Remove Reservation


    public void BacktoUserPanel(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }



    @FXML
    private TextArea Reservations;
    @FXML
    private TextArea Facility;
    public void addReservationsToChoiceBox() throws Exception {
Reservations.setEditable(false);
Facility.setEditable(false);

        int ID = Integer.parseInt(userID.getUser_Id());
        users.load();
        rerservations.load();
        facilitys.load();

        Reservations.setText(rerservations.getAllReservationsByID(ID));
        Facility.setText(facilitys.getAllFacilityByID(ID));


    }

    @FXML
    TextField ReservationNumber;
    @FXML
    Label ErrorChecker2;

    public void DeleteReservation(){
        try {
            if (Reservations.getText().isEmpty() || Facility.getText().isEmpty()) {
                ErrorChecker2.setTextFill(Color.web("#FF0000", 1));
                ErrorChecker2.setText("You have no Reservations to remove!");
                System.out.println("No Reservations to remove");
            } else {

                int FacilityIndexToRemove = Integer.parseInt(ReservationNumber.getText());
                int ReservationIndexToRemove = Integer.parseInt(ReservationNumber.getText());
                facilitys.removeFacility(FacilityIndexToRemove);
                rerservations.removeReservation(ReservationIndexToRemove);
                ErrorChecker2.setTextFill(Color.web("#00FF00", 1));
                ErrorChecker2.setText("Reservation " + ReservationNumber.getText() + " has been removed!");
                Reservations.clear();
                Facility.clear();
                rerservations.save();
                facilitys.save();
                addReservationsToChoiceBox();
            }
        }catch(Exception e){
            ErrorChecker2.setTextFill(Color.web("#FF0000", 1));
            ErrorChecker2.setText("Invalid Reservation number");
            System.out.println("Invalid Reservation number");
        }

    }
    @FXML
    Pane pane;
    @FXML
    ImageView iv;
    private void Image() {
        String Location = getClass().getResource("/Img/pitch.jpg").toString();
        Image image = new Image(Location);
        iv.setImage(image);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

UserID = Integer.parseInt(userID.getUser_Id());
        try {

            loadData();
            users.load();
            rerservations.load();
            facilitys.load();
        } catch (Exception e) {

        }
        try {

        }catch(Exception e){
System.out.println(e.toString());
        }
        String USERID = userID.getUser_Id();
        label.setText(USERID);
        Image();
        try {
            addReservationsToChoiceBox();
        } catch (Exception e) {

        }
    }

}
