package application.service;

import application.domain.Destination;
import application.domain.Destinations;
import application.domain.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Integer> createUserAverage(List<String> cities){
        int numberOfFeatures = destinations.getDestinationByName("London").getFeatures().size();
        List<Integer> point = new ArrayList<>(Collections.nCopies(numberOfFeatures, 0));

        //get Destination version of cities
        List<Destination> cityDestinations = new ArrayList<>();
        for(String city : cities){
            Destination d = destinations.getDestinationByName(city);
            if(d != null){
                cityDestinations.add(d);
            }
        }


        for(Destination d : cityDestinations){
            for(int feature = 0; feature < numberOfFeatures; feature++){
                point.set(feature, point.get(feature) + d.getFeatures().get(feature));
            }
        }

        //TODO: just round down, for now (probably refactor to use Double later)
        if(cityDestinations.size() > 0){
            for(int i = 0; i < numberOfFeatures; i++){
                point.set(i, point.get(i) / cityDestinations.size());
            }
        }

        return point;
    }
}
