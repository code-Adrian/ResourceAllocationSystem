package Project;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReservationFunction {
    private ArrayList<Reservation> reservations = new ArrayList();


    public ReservationFunction(){

    }

    public void addReservation(Reservation reservation) {

        reservations.add(reservation);


    }

    public Reservation getReservation(int Index) {
        for(int check = 0; check <= reservations.size(); ++check) {
            if (reservations.get(Index) == reservations.get(check)) {
                return reservations.get(Index);
            }
        }

        return null;
    }



    public boolean removeReservation(int Index) {
        for(int check = 0; check < reservations.size(); ++check) {
            if (reservations.get(Index) == reservations.get(check)) {
                reservations.remove(Index);
            }
        }

        return false;
    }

    public int getReservationAmountsByID(int ID) {
        if (reservations.size() <= 0) {

        }
            int amount = 0;

            for(int counter = 0; counter < reservations.size(); ++counter) {
                int getID = reservations.get(counter).getReservationId();

                if(getID == ID){
                     amount++;


                }

            }

            return amount;

    }

    public String getAllReservationsByID(int ID) {
        if (reservations.size() <= 0) {

        }
        String reservation = "";

        for(int counter = 0; counter < reservations.size(); ++counter) {
            int getID = reservations.get(counter).getReservationId();

            if(getID == ID){
                reservation = reservation+"Reservation Number: '"+counter+"' \n"
                        + reservations.get(counter).getDate() + " " + reservations.get(counter).getDuration() + " Hour\n================\n";



            }

        }

        return reservation;

    }

    public String getAllReservationsByFacilityID(int ID) {
        if (reservations.size() <= 0) {

        }
        String reservation = "";

        for(int counter = 0; counter < reservations.size(); ++counter) {
            int getID = reservations.get(counter).getFacilityid();
System.out.println(getID);
            System.out.println(ID);
            if(getID == ID){
                reservation = reservation+"Reservation Number: '"+counter+"' \n"
                        + reservations.get(counter).getDate() + " " + reservations.get(counter).getDuration() + " Hour\n====================\n";



            }

        }

        return reservation;

    }

    public void RemoveAllReservationsyByID(int ID) {
        if (reservations.size() <= 0) {

        }


        for(int counter = 0; counter < reservations.size(); ++counter) {
            int getID = reservations.get(counter).getUser_id();

            if(getID == ID){
                reservations.remove(counter);

            }
        }
    }

    public boolean checkIfFacilityIDExists(int FacilityID){
        for(int check = 0; check < reservations.size(); ++check) {

            if(FacilityID == reservations.get(check).getFacilityid()){
                return true;
            }
        }

        return false;
    }

    public String getAllReservations() {
        if (reservations.size() <= 0) {

        }
        String fac = "";

        for(int counter = 0; counter < reservations.size(); ++counter) {


            fac = fac + " "+counter +": Reservation: \n Date: "+ reservations.get(counter).getDate() + " Duration: "+ reservations.get(counter).getDuration() + " Hour"+ " User_ID: "+reservations.get(counter).getUser_id()
                    +"\n==============================\n";




        }

        return fac;

    }

    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("ReservationData.xml"));
        reservations = (ArrayList)is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("ReservationData.xml"));
        out.writeObject(reservations);
        out.close();
    }
}
