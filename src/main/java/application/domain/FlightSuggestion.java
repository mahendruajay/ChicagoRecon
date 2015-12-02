package application.domain;

/**
 * Created by rellison on 11/30/15.
 */
public class FlightSuggestion {
    private String price;
    private String departureDate;
    private String returnDate;
    private Airport originAirport;
    private Airport destinationAirport;
    private DestinationDetails destinationDetails;
    private CruiseSuggestion cruiseSuggestion;
    private String displayDepartureDate;
    private String displayReturnDate;

    public FlightSuggestion(String price, String departureDate, String returnDate, Airport originAirport, Airport destinationAirport, DestinationDetails destinationDetails, CruiseSuggestion cruiseSuggestion,
                            String displayDepartureDate, String displayReturnDate) {
        this.price = price;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.destinationDetails = destinationDetails;
        this.cruiseSuggestion = cruiseSuggestion;
        this.displayDepartureDate = displayDepartureDate;
        this.displayReturnDate = displayReturnDate;
    }

    public String getPrice() {
        return price;
    }

    public String getDepartureDate() {
        return departureDate;
    }
    

    public Airport getOriginAirport() {
        return originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public DestinationDetails getDestinationDetails() {
        return destinationDetails;
    }

    public CruiseSuggestion getCruiseSuggestion() {
        return cruiseSuggestion;
    }

    public String getDisplayDepartureDate() {
        return displayDepartureDate;
    }

    public String getDisplayReturnDate() {
        return displayReturnDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getDeeplinkURL() {
        return "/api/flight/search?ToAirport=" + getDestinationAirport().getCode()
                +"&FromAirport="+getOriginAirport().getCode()+"&startDate="+getDepartureDate()+
                "&returnDate=" + getReturnDate();
    }
}
