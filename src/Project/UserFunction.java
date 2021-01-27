package Project;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserFunction {

    private ArrayList<User> users = new ArrayList();


    public UserFunction(){

    }

    public void addUser(User user) {

        users.add(user);


    }

    public User getUser(int Index) {
        for(int check = 0; check <= users.size(); ++check) {
            if (users.get(Index) == users.get(check)) {
                return users.get(Index);
            }
        }

        return null;
    }

    public boolean removeUser(int Index) {
        for(int check = 0; check < users.size(); ++check) {
            if (users.get(Index) == users.get(check)) {
                users.remove(Index);
            }
        }

        return false;
    }


    public int numberOfUsers() {

        return users.size();

    }
public String PrintAllUsers(){

    if (users.size() <= 0) {
        return "There are no users in the list";
    } else {
        String user = "";

        for(int counter = 0; counter < users.size(); ++counter) {
            user = user + "==============\n";
            user = user +"USER: "+ counter + ": " + "\nUSER_ID: "+users.get(counter).getUser_id() + " <-----\nFirst Name: " + users.get(counter).getfName() + "\nLast Name: " + users.get(counter).getlName() + " " + "\n";
        }

        return user;
    }
}

public boolean checkIfUser_IdExists(int User_Id){
    for(int check = 0; check < users.size(); ++check) {

        if(User_Id == users.get(check).getUser_id()){
            return true;
        }
    }

        return false;
}

    public boolean checkIfEmail_IdExists(String Email){
        for(int check = 0; check < users.size(); ++check) {

            if(Email.contentEquals(users.get(check).getEmail())){
                return true;
            }
        }

        return false;
    }

    public boolean checkIfMobileExists(int Mobile){
        for(int check = 0; check < users.size(); ++check) {

            if(Mobile == (users.get(check).getMobile())){
                return true;
            }
        }

        return false;
    }

    public boolean checkIfPasswordExists(String Password){
        for(int check = 0; check < users.size(); ++check) {

            if(Password.contentEquals(users.get(check).getPassword())){
                return true;
            }
        }

        return false;
    }

    public String getUserByEmail(String Email) {
        if (users.size() <= 0) {
            return "There are no users stored in the list";
        } else {
            String user = "";

            for(int counter = 0; counter < users.size(); ++counter) {
                String getEmail = users.get(counter).getEmail();

                Boolean isFound = getEmail.contentEquals(Email);
                if (isFound) {
                    user = user +users.get(counter).getEmail();
                }
            }

            return user;
        }
    }

    public String getUserByPassword(String Password) {
        if (users.size() <= 0) {
            return "There are no users stored in the list";
        } else {
            String user = "";

            for(int counter = 0; counter < users.size(); ++counter) {
                String getPassword = users.get(counter).getPassword();

                Boolean isFound = getPassword.contentEquals(Password);
                if (isFound) {
                    user = user +users.get(counter).getPassword();
                }
            }

            return user;
        }
    }

    public String getUserByIdentifierFromEmail(String Email) {
        if (users.size() <= 0) {
            return "There are no users stored in the list";
        } else {
            String user = "";

            for(int counter = 0; counter < users.size(); ++counter) {
                String getEmail = users.get(counter).getEmail();

                Boolean isFound = getEmail.contentEquals(Email);
                if (isFound) {
                    user = user +users.get(counter).getUser_id();
                }
            }

            return user;
        }
    }

    public int getUserIndexByID(int ID) {
        if (users.size() <= 0) {

        }

            int user = -1;

            for (int counter = 0; counter < users.size(); ++counter) {
                int getID = users.get(counter).getUser_id();

                if (getID == ID)
                    user = counter;

            }

            return user;

    }




    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("UserData.xml"));
        users = (ArrayList)is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("UserData.xml"));
        out.writeObject(users);
        out.close();
    }


}
