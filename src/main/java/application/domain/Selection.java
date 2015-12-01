package application.domain;

/**
 * Created by amahendru on 12/1/15.
 */
public class Selection {

    private String cityName;
    private String price;

    public Selection(String cityName, String price) {
        this.cityName = cityName;
        this.price = price;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPrice() {
        return price;
    }
}
