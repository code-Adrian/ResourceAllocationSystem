package Project;

public class Facility {

    public Facility(int facilityId, String facilityName, int user_id) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.user_id = user_id;
    }

    private int facilityId;
    private String facilityName;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private int user_id;




    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "facilityId=" + facilityId +
                ", facilityName='" + facilityName + '\'' +
                '}';
    }


}
