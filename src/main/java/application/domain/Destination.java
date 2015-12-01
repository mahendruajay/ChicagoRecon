package application.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rellison on 12/1/15.
 */
public class Destination {
    /*
    Features (Scale 1-10):

    Price (Maybe 1-10, 1 per 150 dollars?)
    In United States
    In Europe
    Beach
    Big City
    Historic
    Outdoor Activities
    Family Friendly
    Entertainment
    Casinos
    Great Dining
    Warm Destination
    Romantic Getaway
    Cruises
    */

    private String city;
    private String state; //if in US
    private String country;
    private List<Integer> features;

    public Destination(String city,
                       String state,
                       String country,
                       int price,
                       int inUs,
                       int inEurope,
                       int beach,
                       int bigCity,
                       int historic,
                       int outdoorActivities,
                       int familyFriendly,
                       int entertainment,
                       int casinos,
                       int greatDining,
                       int warmDestination,
                       int romantic,
                       int cruises){
        this.city = city;
        this.state = state;
        this.country = country;
        features = new ArrayList<>();
        features.add(price);
        features.add(inUs);
        features.add(inEurope);
        features.add(beach);
        features.add(bigCity);
        features.add(historic);
        features.add(outdoorActivities);
        features.add(familyFriendly);
        features.add(entertainment);
        features.add(casinos);
        features.add(greatDining);
        features.add(warmDestination);
        features.add(romantic);
        features.add(cruises);
    }

    public String getCity() { return city; }

    public String getState() { return state; }

    public String getCountry() { return country; }

    public List<Integer> getFeatures(){ return features; }
}
