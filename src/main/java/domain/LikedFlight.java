package domain;

/**
 * Created by rellison on 11/30/15.
 */
public class LikedFlight {

    private String userId;
    private String flightId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
