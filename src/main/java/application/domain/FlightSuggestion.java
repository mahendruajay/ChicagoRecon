package application.domain;

/**
 * Created by rellison on 11/30/15.
 */
public class FlightSuggestion {
    private String price;
    private String departureDate;
    private Airport originAirport;
    private Airport destinationAirport;
    private DestinationDetails destinationDetails;
    private CruiseSuggestion cruiseSuggestion;

    public FlightSuggestion(String price, String departureDate, Airport originAirport, Airport destinationAirport, DestinationDetails destinationDetails, CruiseSuggestion cruiseSuggestion) {
        this.price = price;
        this.departureDate = departureDate;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.destinationDetails = destinationDetails;
        this.cruiseSuggestion = cruiseSuggestion;
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
}
