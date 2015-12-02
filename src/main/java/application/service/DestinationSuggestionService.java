package application.service;

import application.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public DestinationSuggestionService(){
        destinations = new Destinations();
    }

    public Map<String, Suggestion> getNextDestination(String user, String origin){

        Map<String, Destination> nextPlace = determineNextDestination(user, origin);
        Map<String, Suggestion> retVal = new HashMap<>();
        retVal.put("suggested",
                new Suggestion(nextPlace.get("suggested").getCity(), nextPlace.get("suggested").getAirportCodes(), getSpecialLabels(nextPlace.get("suggested")))
        );
        retVal.put("followingIfLiked",
                new Suggestion(nextPlace.get("followingIfLiked").getCity(), nextPlace.get("followingIfLiked").getAirportCodes(), getSpecialLabels(nextPlace.get("followingIfLiked")))
        );
        retVal.put("followingIfDisliked",
                new Suggestion(nextPlace.get("followingIfDisliked").getCity(), nextPlace.get("followingIfDisliked").getAirportCodes(), getSpecialLabels(nextPlace.get("followingIfDisliked")))
        );

        log.info("suggestion: " + nextPlace.get("suggested").getCity() + " nextIfLiked: " + nextPlace.get("followingIfLiked").getCity() + " nextIfDisiked: " + nextPlace.get("followingIfDisliked").getCity());
        return retVal;
    }

    public void setUserStore(UserStore userStore){
        this.userStore = userStore;
    }

    private Map<String, Destination> determineNextDestination(String userId, String origin){

        User user = null;
        if(userStore.getUsers().containsKey(userId)){
            user = userStore.getUsers().get(userId);
        }

        if(user != null && (user.getLiked().keySet().size() > 0 || user.getDisliked().keySet().size() > 0)){
            //this user has some rating history
            List<Integer> userLikeAveragePoint = createUserAverage(user.getLiked());
            List<Integer> userDislikeAveragePoint = createUserAverage(user.getDisliked());
            List<String> alreadyVisited = new ArrayList<>();
            alreadyVisited.addAll(user.getLiked().keySet());
            alreadyVisited.addAll(user.getDisliked().keySet());

            //if already viewed all, show a liked destination again
            if(alreadyVisited.size() == destinations.getDestinations().size()){
                Map<String, Destination> returnVal = new HashMap<>();
                Random rn = new Random();
                List<String> liked = new ArrayList<>();
                liked.addAll(user.getLiked().keySet());
                if(liked.size() > 0) {
                    returnVal.put("suggested", destinations.getDestinationByName(liked.get(rn.nextInt(liked.size()))));
                    returnVal.put("followingIfLiked", destinations.getDestinationByName(liked.get(rn.nextInt(liked.size()))));
                    returnVal.put("followingIfDisliked", destinations.getDestinationByName(liked.get(rn.nextInt(liked.size()))));
                    return returnVal;
                } else {
                    returnVal.put("suggested", destinations.getDestinationByName(alreadyVisited.get(rn.nextInt(alreadyVisited.size()))));
                    returnVal.put("followingIfLiked", destinations.getDestinationByName(alreadyVisited.get(rn.nextInt(alreadyVisited.size()))));
                    returnVal.put("followingIfDisliked", destinations.getDestinationByName(alreadyVisited.get(rn.nextInt(alreadyVisited.size()))));
                    return returnVal;
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

            Map<String, Destination> returnVal = new HashMap<>();
            //best place
            returnVal.put("suggested", destinations.getDestinationByName(bestNextCity));

            //now suppose we rated from currently showing...
            alreadyVisited.add(bestNextCity);
            if(alreadyVisited.size() == destinations.getDestinations().size()){
                Random rn = new Random();
                List<String> liked = new ArrayList<>();
                liked.addAll(user.getLiked().keySet());
                if(liked.size() > 0) {
                    returnVal.put("followingIfLiked", destinations.getDestinationByName(liked.get(rn.nextInt(liked.size()))));
                    returnVal.put("followingIfDisliked", destinations.getDestinationByName(liked.get(rn.nextInt(liked.size()))));
                    return returnVal;
                } else {
                    returnVal.put("followingIfLiked", destinations.getDestinationByName(alreadyVisited.get(rn.nextInt(alreadyVisited.size()))));
                    returnVal.put("followingIfD" +
                            "isliked", destinations.getDestinationByName(alreadyVisited.get(rn.nextInt(alreadyVisited.size()))));
                    return returnVal;
                }
            }

            //suppose they like current city
            Map<String, Selection> futureUserLiked = new HashMap<>();
            for(String liked : user.getLiked().keySet()){
                //TODO: if we consider price, we'll need to change 0 to reflect the price of currently showing
                futureUserLiked.put(liked, new Selection(null, null, null, null, "0"));
            }
            futureUserLiked.put(bestNextCity, new Selection(null, null, null, null, "0"));

            List<Integer> userLikeAveragePointAfterLikingSuggested = createUserAverage(futureUserLiked);
            List<Integer> userDislikeAveragePointAfterLikingSuggested = createUserAverage(user.getDisliked());

            Map<String, Double> distNewToLikedPointsIfLikeSuggested = distancesPointToNewDestinations(userLikeAveragePointAfterLikingSuggested, alreadyVisited);
            Map<String, Double> distNewToDislikedPointsIfLikeSuggested = distancesPointToNewDestinations(userDislikeAveragePointAfterLikingSuggested, alreadyVisited);
            Map<String, Double> cityNetDistIfLikeSuggested = findNetDists(distNewToLikedPointsIfLikeSuggested, distNewToDislikedPointsIfLikeSuggested);

            Double minDistanceIfLikeSuggested = 100000000.0;
            String bestNextCityIfLikeSuggested = "";
            for(String city : cityNetDistIfLikeSuggested.keySet()){
                if(cityNetDistIfLikeSuggested.get(city) < minDistanceIfLikeSuggested){
                    minDistanceIfLikeSuggested = cityNetDistIfLikeSuggested.get(city);
                    bestNextCityIfLikeSuggested = city;
                }
            }
            returnVal.put("followingIfLiked", destinations.getDestinationByName(bestNextCityIfLikeSuggested));

            //suppose they dislike current city
            Map<String, Selection> futureUserDisliked = new HashMap<>();
            for(String disliked : user.getDisliked().keySet()){
                //TODO: if we consider price, we'll need to change 0 to reflect the price of currently showing
                futureUserDisliked.put(disliked, new Selection(null, null, null, null, "0"));
            }
            futureUserDisliked.put(bestNextCity, new Selection(null, null, null, null, "0"));

            List<Integer> userLikeAveragePointAfterDislikingSuggested = createUserAverage(user.getLiked());
            List<Integer> userDislikeAveragePointAfterDislikingSuggested = createUserAverage(futureUserDisliked);

            Map<String, Double> distNewToLikedPointsIfDislikeSuggested = distancesPointToNewDestinations(userLikeAveragePointAfterDislikingSuggested, alreadyVisited);
            Map<String, Double> distNewToDislikedPointsIfDislikeSuggested = distancesPointToNewDestinations(userDislikeAveragePointAfterDislikingSuggested, alreadyVisited);
            Map<String, Double> cityNetDistIfDislikeSuggested = findNetDists(distNewToLikedPointsIfDislikeSuggested, distNewToDislikedPointsIfDislikeSuggested);

            Double minDistanceIfDislikeSuggested = 100000000.0;
            String bestNextCityIfDislikeSuggested = "";
            for(String city : cityNetDistIfDislikeSuggested.keySet()){
                if(cityNetDistIfDislikeSuggested.get(city) < minDistanceIfDislikeSuggested){
                    minDistanceIfDislikeSuggested = cityNetDistIfDislikeSuggested.get(city);
                    bestNextCityIfDislikeSuggested = city;
                }
            }
            returnVal.put("followingIfDisliked", destinations.getDestinationByName(bestNextCityIfDislikeSuggested));

            return returnVal;
        } else{
            //user has no rating history, show a random destination
            Random rn = new Random();
            Map<String, Destination> returnVal = new HashMap<>();

            Destination suggeseted = destinations.getDestinations().get(rn.nextInt(destinations.getDestinations().size()));
            returnVal.put("suggested", suggeseted);

            List<String> alreadyVisited = new ArrayList<>();
            alreadyVisited.add(suggeseted.getCity());

            //suppose they like current city
            Map<String, Selection> futureUserLiked = new HashMap<>();
            futureUserLiked.put(suggeseted.getCity(), new Selection(null, null, null, null, "0"));
            Map<String, Selection> futureUserDisliked = new HashMap<>();
            List<Integer> userLikeAveragePointAfterLikingSuggested = createUserAverage(futureUserLiked);
            List<Integer> userDislikeAveragePointAfterLikingSuggested = createUserAverage(futureUserDisliked);
            Map<String, Double> distNewToLikedPointsIfLikeSuggested = distancesPointToNewDestinations(userLikeAveragePointAfterLikingSuggested, alreadyVisited);
            Map<String, Double> distNewToDislikedPointsIfLikeSuggested = distancesPointToNewDestinations(userDislikeAveragePointAfterLikingSuggested, alreadyVisited);
            Map<String, Double> cityNetDistIfLikeSuggested = findNetDists(distNewToLikedPointsIfLikeSuggested, distNewToDislikedPointsIfLikeSuggested);
            Double minDistanceIfLikeSuggested = 100000000.0;
            String bestNextCityIfLikeSuggested = "";
            for(String city : cityNetDistIfLikeSuggested.keySet()){
                if(cityNetDistIfLikeSuggested.get(city) < minDistanceIfLikeSuggested){
                    minDistanceIfLikeSuggested = cityNetDistIfLikeSuggested.get(city);
                    bestNextCityIfLikeSuggested = city;
                }
            }
            returnVal.put("followingIfLiked", destinations.getDestinationByName(bestNextCityIfLikeSuggested));

            //suppose they dislike current city
            futureUserLiked = new HashMap<>();
            futureUserDisliked = new HashMap<>();
            futureUserDisliked.put(suggeseted.getCity(), new Selection(null, null, null, null, "0"));
            List<Integer> userLikeAveragePointAfterDislikingSuggested = createUserAverage(futureUserLiked);
            List<Integer> userDislikeAveragePointAfterDislikingSuggested = createUserAverage(futureUserDisliked);
            Map<String, Double> distNewToLikedPointsIfDislikeSuggested = distancesPointToNewDestinations(userLikeAveragePointAfterDislikingSuggested, alreadyVisited);
            Map<String, Double> distNewToDislikedPointsIfDislikeSuggested = distancesPointToNewDestinations(userDislikeAveragePointAfterDislikingSuggested, alreadyVisited);
            Map<String, Double> cityNetDistIfDislikeSuggested = findNetDists(distNewToLikedPointsIfDislikeSuggested, distNewToDislikedPointsIfDislikeSuggested);
            Double minDistanceIfDislikeSuggested = 100000000.0;
            String bestNextCityIfDislikeSuggested = "";
            for(String city : cityNetDistIfDislikeSuggested.keySet()){
                if(cityNetDistIfDislikeSuggested.get(city) < minDistanceIfDislikeSuggested){
                    minDistanceIfDislikeSuggested = cityNetDistIfDislikeSuggested.get(city);
                    bestNextCityIfDislikeSuggested = city;
                }
            }
            returnVal.put("followingIfDisliked", destinations.getDestinationByName(bestNextCityIfDislikeSuggested));

            return returnVal;
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
        int numberOfFeatures = destinations.getDestinationByName("Austin").getFeatures().size();
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
            Double netWeight = distNewToLikedPoints.get(city) - (0.4 * distNewToDislikedPoints.get(city));
            netDists.put(city, netWeight);
        }

        return netDists;
    }

    private Map<String, Double> distancesPointToNewDestinations(List<Integer> point, List<String> alreadyVisited){
        Map<String, Double> distPointToNewDests = new HashMap<>();
        List<Double> normPoint = normalizeFeatureVector(point);
        destinations.getDestinations().forEach(d -> {
            if(!alreadyVisited.contains(d.getCity())) {
                List<Double> normPoint2 = normalizeFeatureVector(d.getFeatures());
                distPointToNewDests.put(d.getCity(), distance(normPoint, normPoint2));
            }
        });

        return distPointToNewDests;
    }

    private Double distance(List<Double> pt1, List<Double>pt2){
        Double distance = 0.0;

        for(int feature = 0; feature < pt1.size(); feature++){
            distance += Math.pow(pt1.get(feature) - pt2.get(feature), 2);
        }

        return Math.pow(distance, 0.5);
    }

    private List<Double> normalizeFeatureVector(List<Integer> vector){
        int vectorSum = 1;
        for(Integer feature : vector){
            vectorSum += feature;
        }

        List<Double> normalizedFeatureVector = new ArrayList<>();
        for(Integer feature : vector){
            normalizedFeatureVector.add(feature.doubleValue() / vectorSum);
            normalizedFeatureVector.add(feature.doubleValue());
        }

        return normalizedFeatureVector;
    }
}
