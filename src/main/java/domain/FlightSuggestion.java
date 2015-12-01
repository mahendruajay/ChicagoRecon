package domain;

import java.math.BigDecimal;

/**
 * Created by rellison on 11/30/15.
 */
public class FlightSuggestion {
    private BigDecimal price;
    private String departureDate;
    private String originAirportCode;
    private String originCity;
    private String destinationAirportCode;
    private String destinationCity;
    private String destinationImage;
    private DestinationDetails destinationDetails;

    public FlightSuggestion(BigDecimal price, String departureDate, String originAirportCode, String originCity, String destinationAirportCode, String destinationCity, String destinationImage, DestinationDetails destinationDetails) {
        this.price = price;
        this.departureDate = departureDate;
        this.originAirportCode = originAirportCode;
        this.originCity = originCity;
        this.destinationAirportCode = destinationAirportCode;
        this.destinationCity = destinationCity;
        this.destinationImage = destinationImage;
        this.destinationDetails = destinationDetails;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getOriginAirportCode() {
        return originAirportCode;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String getDestinationImage() {
        return destinationImage;
    }

    public DestinationDetails getDestinationDetails() {
        return destinationDetails;
    }
}
