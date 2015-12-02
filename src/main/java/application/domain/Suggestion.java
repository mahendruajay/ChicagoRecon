package application.domain;

import java.util.List;

/**
 * Created by rellison on 12/1/15.
 */
public class Suggestion {

    private String cityName;
    private List<String> airportCodes;
    private List<String> labels;

    public Suggestion(String cityName, List<String> airportCodes, List<String> labels) {
        this.cityName = cityName;
        this.airportCodes = airportCodes;
        this.labels = labels;
    }

    public String getCityName() {
        return cityName;
    }

    public List<String> getAirportCodes() {
        return airportCodes;
    }

    public List<String> getLabels() {
        return labels;
    }
}
