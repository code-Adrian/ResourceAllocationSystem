package Project;

public class Reservation {
    private int facilityid;
    private int reservationId;
    private String date;
    private int duration;
    private int user_id;


    public Reservation(int facilityid, int reservationId, String date, int duration, int user_id) {
        this.facilityid = facilityid;
        this.reservationId = reservationId;
        this.date = date;
        this.duration = duration;
        this.user_id = user_id;
    }


    public int getFacilityid() {
        return facilityid;
    }

    public void setFacilityid(int facilityid) {
        this.facilityid = facilityid;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "facilityid=" + facilityid +
                ", reservationId=" + reservationId +
                ", date=" + date +
                ", duration=" + duration +
                ", user_id=" + user_id +
                '}';
    }



}
