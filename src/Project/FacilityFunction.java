package Project;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FacilityFunction {
    private ArrayList<Facility> facilitys = new ArrayList();


    public FacilityFunction(){

    }

    public void addFacility(Facility facility) {

        facilitys.add(facility);


    }

    public Facility getFacility(int Index) {
        for(int check = 0; check <= facilitys.size(); ++check) {
            if (facilitys.get(Index) == facilitys.get(check)) {
                return facilitys.get(Index);
            }
        }

        return null;
    }

    public boolean removeFacility(int Index) {
        for(int check = 0; check < facilitys.size(); ++check) {
            if (facilitys.get(Index) == facilitys.get(check)) {
                facilitys.remove(Index);
            }
        }

        return false;
    }

    public int getFacilityAmountsByID(int ID) {
        if (facilitys.size() <= 0) {

        }
        int amount = 0;

        for(int counter = 0; counter < facilitys.size(); ++counter) {
            int getID = facilitys.get(counter).getFacilityId();

            if(getID == ID){
                amount++;


            }

        }

        return amount;

    }

    public String getAllFacilityByID(int ID) {
        if (facilitys.size() <= 0) {

        }
        String fac = "";

        for(int counter = 0; counter < facilitys.size(); ++counter) {
            int getID = facilitys.get(counter).getUser_id();

            if(getID == ID){
                fac = fac + " "+counter +": Facility: \n"+ facilitys.get(counter).getFacilityName()+"\n===============\n";


            }

        }

        return fac;

    }

    public String getAllFacilitys() {
        if (facilitys.size() <= 0) {

        }
        String fac = "";

        for(int counter = 0; counter < facilitys.size(); ++counter) {


                fac = fac + " "+counter +": Facility: \n"+ facilitys.get(counter).getFacilityName()+"\n===============\n";




        }

        return fac;

    }

    public String getAllFacilityByFacilityID(int ID) {
        if (facilitys.size() <= 0) {

        }
        String fac = "";

        for(int counter = 0; counter < facilitys.size(); ++counter) {
            int getID = facilitys.get(counter).getFacilityId();

            if(getID == ID){
                fac = fac + " "+counter +": "+ facilitys.get(counter).getFacilityName()+"\n";


            }

        }

        return fac;

    }

    public String getFacilityIDByID(int ID) {
        if (facilitys.size() <= 0) {

        }
        String fac = "";

        for(int counter = 0; counter < facilitys.size(); ++counter) {
            int getID = facilitys.get(counter).getUser_id();

            if(getID == ID){
                fac = fac + " "+counter +": "+ facilitys.get(counter).getFacilityId();


            }

        }

        return fac;

    }

    public boolean checkIfFacilityIDExists(int FacilityID){
        for(int check = 0; check < facilitys.size(); ++check) {

            if(FacilityID == facilitys.get(check).getFacilityId()){
                return true;
            }
        }

        return false;
    }

    public void RemoveAllFacilityByID(int ID) {
        if (facilitys.size() <= 0) {

        }
        
        for(int counter = 0; counter < facilitys.size(); ++counter) {
            int getID = facilitys.get(counter).getUser_id();

            if(getID == ID){

                facilitys.remove(counter);

            }
        }
    }

    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("FacilityData.xml"));
        facilitys = (ArrayList)is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("FacilityData.xml"));
        out.writeObject(facilitys);
        out.close();
    }
}
