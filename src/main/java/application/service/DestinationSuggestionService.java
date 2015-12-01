package application.service;

import application.domain.Destination;
import application.domain.Destinations;
import application.domain.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rellison on 12/1/15.
 */
@Component
public class DestinationSuggestionService {

    private Destinations destinations;

    public DestinationSuggestionService(){
        destinations = new Destinations();
    }

    public Suggestion getNextDestination(String user, String origin){

        Destination nextPlace = determineNextDestination(user, origin);
        return new Suggestion(nextPlace.getCity(), "CODE", getTopLabels(nextPlace));
    }

    private Destination determineNextDestination(String user, String origin){

        //for now, always say London
        return destinations.getDestinationByName("London");
    }

    private List<String> getTopLabels(Destination destination){
        List<String> topLabels = new ArrayList<>();
        List<Integer> features = destination.getFeatures();
        if(features.get(4) > 7) {
            topLabels.add("Great Beaches");
        }
        if(features.get(7) > 7) {
            topLabels.add("Family Friendly");
        }
        if(features.get(12) > 7) {
            topLabels.add("Romantic Getaway");
        }
        if(features.get(13) > 7) {
            topLabels.add("Cruises");
        }
        return topLabels;
    }
}
