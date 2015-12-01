package application.domain;

import java.util.List;

/**
 * Created by predding on 12/1/15.
 */
public class CruiseSuggestion {
    private String cruiseSearchDeepLink;
    private List<String> destinations;

    public CruiseSuggestion(String cruiseSearchDeepLink, List<String> destinations) {
        this.cruiseSearchDeepLink = cruiseSearchDeepLink;
        this.destinations = destinations;
    }

    public String getCruiseSearchDeepLink() {
        return cruiseSearchDeepLink;
    }

    public List<String> getDestinations() {
        return destinations;
    }
}
