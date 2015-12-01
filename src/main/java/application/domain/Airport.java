package application.domain;

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

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }
}
