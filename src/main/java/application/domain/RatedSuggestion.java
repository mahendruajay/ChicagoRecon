package application.domain;

/**
 * Created by rellison on 11/30/15.
 */
public class RatedSuggestion {

    private String userId;
    private String arrivalCity;
    private String departureCity;
    private String departureDate;
    private String arrivalDate;
    private boolean liked;
    private String price;

    public RatedSuggestion(){}

    public RatedSuggestion(String userId, String arrivalCity, String departureCity, String departureDate, String arrivalDate, boolean liked, String price) {
        this.userId = userId;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.liked = liked;
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public boolean isLiked() {
        return liked;
    }

    public String getPrice() {
        return price;
    }
}
