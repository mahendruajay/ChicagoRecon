package application.domain;

/**
 * Created by rellison on 11/30/15.
 */
public class RatedSuggestion {

    private String userId;
    private String city;
    private String rating;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
