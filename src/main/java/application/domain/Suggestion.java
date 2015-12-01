package application.domain;

import java.util.List;

/**
 * Created by rellison on 12/1/15.
 */
public class Suggestion {

    private String cityName;
    private String airportCode;
    private List<String> labels;

    public Suggestion(String cityName, String airportCode, List<String> labels) {
        this.cityName = cityName;
        this.airportCode = airportCode;
        this.labels = labels;
    }

    public String getCityName() {
        return cityName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public List<String> getLabels() {
        return labels;
    }
}
