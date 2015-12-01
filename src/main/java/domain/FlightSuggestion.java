package domain;

import java.math.BigDecimal;

/**
 * Created by rellison on 11/30/15.
 */
public class FlightSuggestion {
    private BigDecimal price;
    private String departureDate;
    private Airport originAirport;
    private Airport destinationAirport;
    private DestinationDetails destinationDetails;

    public FlightSuggestion(BigDecimal price, String departureDate, Airport originAirport, Airport destinationAirport, DestinationDetails destinationDetails) {
        this.price = price;
        this.departureDate = departureDate;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.destinationDetails = destinationDetails;
    }

    public BigDecimal getPrice() {
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
}
