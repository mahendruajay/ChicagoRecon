package application.service;

import application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by rellison on 12/1/15.
 */
@Component
public class DestinationSuggestionService {

    @Autowired
    private UserStore userStore;

    private Destinations destinations;

    public DestinationSuggestionService(){
        destinations = new Destinations();
    }

    public Suggestion getNextDestination(String user, String origin){

        Destination nextPlace = determineNextDestination(user, origin);
        return new Suggestion(nextPlace.getCity(), nextPlace.getAirportCodes(), getSpecialLabels(nextPlace));
    }

    public void setUserStore(UserStore userStore){
        this.userStore = userStore;
    }

    private Destination determineNextDestination(String userId, String origin){

        User user = null;
        if(userStore.getUsers().containsKey(userId)){
            user = userStore.getUsers().get(userId);
        }

        if(user != null && user.getLiked().keySet().size() == 0 && user.getDisliked().keySet().size() == 0){
            //this user has some rating history
            List<Integer> userLikeAveragePoint = createUserAverage(user.getLiked());
            List<Integer> userDislikeAveragePoint = createUserAverage(user.getDisliked());
            List<String> alreadyVisited = new ArrayList<>();
            alreadyVisited.addAll(user.getLiked().keySet());
            alreadyVisited.addAll(user.getDisliked().keySet());

            //if already viewed all, show a liked destination again
            if(alreadyVisited.size() == destinations.getDestinations().size()){
                Random rn = new Random();
                List<String> liked = new ArrayList<>();
                liked.addAll(user.getLiked().keySet());
                if(liked.size() > 0) {
                    return destinations.getDestinationByName(liked.get(rn.nextInt(liked.size())));
                } else {
                    //TODO: fix this path later
                    return destinations.getDestinationByName("London");
                }
            }

            //at least one destination is remaining
            Map<String, Double> distNewToLikedPoints = distancesPointToNewDestinations(userLikeAveragePoint, alreadyVisited);
            Map<String, Double> distNewToDislikedPoints = distancesPointToNewDestinations(userDislikeAveragePoint, alreadyVisited);
            Map<String, Double> cityNetDist = findNetDists(distNewToLikedPoints, distNewToDislikedPoints);

            Double minDistance = 1000000.0;
            String bestNextCity = "";
            for(String city : cityNetDist.keySet()){
                if(cityNetDist.get(city) < minDistance){
                    minDistance = cityNetDist.get(city);
                    bestNextCity = city;
                }
            }

            //for now, always say London
            return destinations.getDestinationByName(bestNextCity);
        } else{
            //user has no rating history, show a random destination
            Random rn = new Random();
            return destinations.getDestinations().get(rn.nextInt(destinations.getDestinations().size()));
        }
    }

    private List<String> getSpecialLabels(Destination destination){
        List<String> topLabels = new ArrayList<>();
        List<Integer> features = destination.getFeatures();
        if(features.get(3) > 7) {
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

    public List<Integer> createUserAverage(Map<String, Selection> cities){
        int numberOfFeatures = destinations.getDestinationByName("London").getFeatures().size();
        List<Integer> point = new ArrayList<>(Collections.nCopies(numberOfFeatures, 0));

        //get Destination version of cities
        List<Destination> cityDestinations = new ArrayList<>();
        for(String city : cities.keySet()){
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

    private Map<String, Double> findNetDists(Map<String, Double> distNewToLikedPoints, Map<String, Double> distNewToDislikedPoints){
        Map<String, Double> netDists = new HashMap<>();

        for(String city: distNewToLikedPoints.keySet()){
            //We want a minimum netWeight for our best suggestion. Consider liked more than disliked.
            Double netWeight = distNewToLikedPoints.get(city) - (0.5 * distNewToDislikedPoints.get(city));
            netDists.put(city, netWeight);
        }

        return netDists;
    }

    private Map<String, Double> distancesPointToNewDestinations(List<Integer> point, List<String> alreadyVisited){
        Map<String, Double> distPointToNewDests = new HashMap<>();
        destinations.getDestinations().forEach(d -> {
            if(!alreadyVisited.contains(d.getCity())) {
                distPointToNewDests.put(d.getCity(), distance(point, d.getFeatures()));
            }
        });
        return distPointToNewDests;
    }

    private Double distance(List<Integer> pt1, List<Integer>pt2){
        Double distance = 0.0;

        for(int feature = 0; feature < pt1.size(); feature++){
            Math.pow(pt1.get(feature) - pt2.get(feature), 2);
        }
        return 0.0;
    }
}
