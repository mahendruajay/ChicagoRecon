package application.domain;

/**
 * Created by rellison on 11/30/15.
 */
public class RatedSuggestion {

    private String userId;
    private String cityName;
    private boolean selected;
    private String price;

    public RatedSuggestion(String userId, String cityName, boolean selected, String price) {
        this.userId = userId;
        this.cityName = cityName;
        this.selected = selected;
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public String getCityName() {
        return cityName;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getPrice() {
        return price;
    }
}
