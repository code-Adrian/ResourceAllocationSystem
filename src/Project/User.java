package Project;

public class User {
    private String fName;
    private String lName;
    private String role;
    private int mobile;
    private String email;
    private String userType;
    private String password;


    public User(String fName, String lName, String role, int mobile, String email, String userType, String password, int user_id) {
        this.fName = fName;
        this.lName = lName;
        this.role = role;
        this.mobile = mobile;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.user_id = user_id;
    }
//User uses this to register
    public User(String fName, String lName, int mobile, String email, String password, int user_id) {
        this.fName = fName;
        this.lName = lName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.user_id = user_id;
    }

    public User(String email,String password,int mobile){
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getRole() {
        return "Customer";
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int user_id;

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", role='" + role + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", password='" + password + '\'' +
                '}';
    }





}
