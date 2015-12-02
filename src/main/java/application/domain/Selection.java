package application.domain;

/**
 * Created by amahendru on 12/1/15.
 */
public class Selection {

    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private String arrivalDate;
    private String price;


    public Selection(String departureCity, String arrivalCity, String departureDate, String arrivalDate, String price) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getPrice() {
        return price;
    }
}
