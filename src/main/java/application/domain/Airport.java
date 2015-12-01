package application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by amahendru on 11/30/15.
 */
public class Airport {
    private String code;
    private String city;

    public Airport(String code, String city) {
        this.code = code;
        this.city = city;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }
}
