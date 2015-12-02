package application.service;

import application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by amahendru on 12/1/15.
 */
@Service
public class UserStoreService {

    @Autowired
    private UserStore userStore;

    @Autowired
    ImageService imageService;

    public void storeUserResponse(RatedSuggestion ratedSuggestion) {

        synchronized (userStore) {
            String userID = ratedSuggestion.getUserId();
            if (null != userStore && null == userStore.getUsers().get(userID)) {
                User user = new User(userID);
                userStore.getUsers().put(userID, user);
            }

            User user = userStore.getUsers().get(userID);

            Selection selection = new Selection(ratedSuggestion.getDepartureCity(), ratedSuggestion.getArrivalCity(),
                    ratedSuggestion.getDepartureDate(), ratedSuggestion.getArrivalDate(), ratedSuggestion.getPrice());
            if (ratedSuggestion.isLiked()) {
                user.getLiked().put(ratedSuggestion.getArrivalCity(), selection);
            } else {
                user.getDisliked().put(ratedSuggestion.getArrivalCity(), selection);
            }

        }

    }

    public List<FlightSuggestion> getUserLikedHistory(String userID) {
        List<Selection> selections = new ArrayList<>();
        synchronized (userStore) {
            Map<String, Selection> userSelections = userStore.getUsers().get(userID).getLiked();
            for (Selection selection : userSelections.values()) {
                selections.add(selection);
            }
        }
        List<FlightSuggestion> flightSuggestions = new ArrayList<>();

        for (Selection selection : selections) {

            FlightSuggestion flightSuggestion = new FlightSuggestion(selection.getPrice(), selection.getDepartureDate(),
                    selection.getArrivalDate(),
                    new Airport(getCodeFromCityName(selection.getDepartureCity()), selection.getDepartureCity()),
                    new Airport(getCodeFromCityName(selection.getArrivalCity()), selection.getArrivalCity()), null, null, "", "");

            flightSuggestions.add(flightSuggestion);
        }

        return flightSuggestions;
    }

    private String getCodeFromCityName(String city) {
        List<Destination> destinations = new Destinations().getDestinations();
        for (Destination destination : destinations) {
            if(destination.getCity().equalsIgnoreCase(city)) {
                return destination.getAirportCodes().get(0);
            }
        }
        return "CHI";
    }
}
