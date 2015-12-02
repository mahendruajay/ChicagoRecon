package application.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rellison on 12/1/15.
 */
public class Destinations {

    private List<Destination> destinations;
    private Map<String, Destination> destinationMap;

    public Destinations(){
        initDestinationsList();
    }

    public List<Destination> getDestinations(){
        return destinations;
    }

    public Destination getDestinationByName(String city){
        if(destinationMap.containsKey(city)){
            return destinationMap.get(city);
        }
        return null;
    }

    private void initDestinationsList(){

        destinations = new ArrayList<>();

//      New York City, New York
        List<String> airportCodes1 = new ArrayList<>();
        airportCodes1.add("JFK");
        airportCodes1.add("LGA");
        destinations.add(
                new Destination("New York City" /*city*/,
                        "New York" /*state*/,
                        "United States" /*country*/,
                        airportCodes1,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        2 /*beach*/,
                        10 /*bigCity*/,
                        5 /*historic*/,
                        5 /*outdoorActivities*/,
                        5 /*familyFriendly*/,
                        10 /*entertainment*/,
                        0 /*casinos*/,
                        10 /*greatDining*/,
                        0 /*warmDestination*/,
                        3 /*romantic*/,
                        10 /*cruises*/));

//      Charleston, South Carolina
        List<String> airportCodes2 = new ArrayList<>();
        airportCodes2.add("CHS");
        destinations.add(
                new Destination("Charleston" /*city*/,
                        "South Carolina" /*state*/,
                        "United States" /*country*/,
                        airportCodes2,
                        2 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        2 /*beach*/,
                        3 /*bigCity*/,
                        3 /*historic*/,
                        7 /*outdoorActivities*/,
                        2 /*familyFriendly*/,
                        2 /*entertainment*/,
                        0 /*casinos*/,
                        0 /*greatDining*/,
                        4 /*warmDestination*/,
                        0 /*romantic*/,
                        0 /*cruises*/));

//      Las Vegas, Nevada
        List<String> airportCodes3 = new ArrayList<>();
        airportCodes3.add("LAS");
        destinations.add(
                new Destination("Las Vegas" /*city*/,
                        "Nevada" /*state*/,
                        "United States" /*country*/,
                        airportCodes3,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        0 /*beach*/,
                        5 /*bigCity*/,
                        0 /*historic*/,
                        5 /*outdoorActivities*/,
                        0 /*familyFriendly*/,
                        10 /*entertainment*/,
                        10 /*casinos*/,
                        10 /*greatDining*/,
                        7 /*warmDestination*/,
                        0 /*romantic*/,
                        0 /*cruises*/));

//      Seattle, Washington
        List<String> airportCodes4 = new ArrayList<>();
        airportCodes4.add("SEA");
        destinations.add(
                new Destination("Seattle" /*city*/,
                        "Washington" /*state*/,
                        "United States" /*country*/,
                        airportCodes4,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        3 /*beach*/,
                        5 /*bigCity*/,
                        2 /*historic*/,
                        8 /*outdoorActivities*/,
                        8 /*familyFriendly*/,
                        5 /*entertainment*/,
                        0 /*casinos*/,
                        8 /*greatDining*/,
                        0 /*warmDestination*/,
                        2 /*romantic*/,
                        7 /*cruises*/));

//      San Francisco, California
        List<String> airportCodes5 = new ArrayList<>();
        airportCodes5.add("SFO");
        destinations.add(
                new Destination("San Francisco" /*city*/,
                        "California" /*state*/,
                        "United States" /*country*/,
                        airportCodes5,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        7 /*beach*/,
                        5 /*bigCity*/,
                        5 /*historic*/,
                        8 /*outdoorActivities*/,
                        8 /*familyFriendly*/,
                        5 /*entertainment*/,
                        0 /*casinos*/,
                        8 /*greatDining*/,
                        5 /*warmDestination*/,
                        3 /*romantic*/,
                        7 /*cruises*/));

//      Washington DC, District of Columbia
        List<String> airportCodes6 = new ArrayList<>();
        airportCodes6.add("IAD");
        airportCodes6.add("DCA");
        destinations.add(
                new Destination("Washington DC" /*city*/,
                        "District of Columbia" /*state*/,
                        "United States" /*country*/,
                        airportCodes6,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        2 /*beach*/,
                        7 /*bigCity*/,
                        10 /*historic*/,
                        8 /*outdoorActivities*/,
                        8 /*familyFriendly*/,
                        5 /*entertainment*/,
                        0 /*casinos*/,
                        5 /*greatDining*/,
                        0 /*warmDestination*/,
                        0 /*romantic*/,
                        0 /*cruises*/));

//      New Orleans, Louisiana
        List<String> airportCodes7 = new ArrayList<>();
        airportCodes7.add("MSY");
        destinations.add(
                new Destination("New Orleans" /*city*/,
                        "Louisiana" /*state*/,
                        "United States" /*country*/,
                        airportCodes7,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        3 /*beach*/,
                        5 /*bigCity*/,
                        8 /*historic*/,
                        5 /*outdoorActivities*/,
                        5 /*familyFriendly*/,
                        8 /*entertainment*/,
                        8 /*casinos*/,
                        8 /*greatDining*/,
                        6 /*warmDestination*/,
                        5 /*romantic*/,
                        5 /*cruises*/));

//      San Diego, California
        List<String> airportCodes8 = new ArrayList<>();
        airportCodes8.add("SAN");
        destinations.add(
                new Destination("San Diego" /*city*/,
                        "California" /*state*/,
                        "United States" /*country*/,
                        airportCodes8,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        7 /*beach*/,
                        5 /*bigCity*/,
                        2 /*historic*/,
                        7 /*outdoorActivities*/,
                        7 /*familyFriendly*/,
                        5 /*entertainment*/,
                        5 /*casinos*/,
                        5 /*greatDining*/,
                        7 /*warmDestination*/,
                        7 /*romantic*/,
                        7 /*cruises*/));

//      Honolulu, Hawaii
        List<String> airportCodes9 = new ArrayList<>();
        airportCodes9.add("HNL");
        destinations.add(
                new Destination("Honolulu" /*city*/,
                        "Hawaii" /*state*/,
                        "United States" /*country*/,
                        airportCodes9,
                        7 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        10 /*beach*/,
                        5 /*bigCity*/,
                        5 /*historic*/,
                        10 /*outdoorActivities*/,
                        10 /*familyFriendly*/,
                        5 /*entertainment*/,
                        7 /*casinos*/,
                        5 /*greatDining*/,
                        10 /*warmDestination*/,
                        10 /*romantic*/,
                        7 /*cruises*/));

//      Miami Beach, Florida
        List<String> airportCodes10 = new ArrayList<>();
        airportCodes10.add("MIA");
        destinations.add(
                new Destination("Miami Beach" /*city*/,
                        "Florida" /*state*/,
                        "United States" /*country*/,
                        airportCodes10,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        10 /*beach*/,
                        5 /*bigCity*/,
                        0 /*historic*/,
                        8 /*outdoorActivities*/,
                        8 /*familyFriendly*/,
                        7 /*entertainment*/,
                        7 /*casinos*/,
                        8 /*greatDining*/,
                        8 /*warmDestination*/,
                        7 /*romantic*/,
                        10 /*cruises*/));

//      Boston, Massachusetts
        List<String> airportCodes11 = new ArrayList<>();
        airportCodes11.add("BOS");
        destinations.add(
                new Destination("Boston" /*city*/,
                        "Massachusetts" /*state*/,
                        "United States" /*country*/,
                        airportCodes11,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        2 /*beach*/,
                        8 /*bigCity*/,
                        10 /*historic*/,
                        5 /*outdoorActivities*/,
                        5 /*familyFriendly*/,
                        7 /*entertainment*/,
                        4 /*casinos*/,
                        8 /*greatDining*/,
                        0 /*warmDestination*/,
                        2 /*romantic*/,
                        5 /*cruises*/));

//      Orlando, Florida
        List<String> airportCodes12 = new ArrayList<>();
        airportCodes12.add("MCO");
        destinations.add(
                new Destination("Orlando" /*city*/,
                        "Florida" /*state*/,
                        "United States" /*country*/,
                        airportCodes12,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        2 /*beach*/,
                        5 /*bigCity*/,
                        0 /*historic*/,
                        7 /*outdoorActivities*/,
                        10 /*familyFriendly*/,
                        7 /*entertainment*/,
                        5 /*casinos*/,
                        5 /*greatDining*/,
                        7 /*warmDestination*/,
                        0 /*romantic*/,
                        2 /*cruises*/));

//      Nashville, Tennessee
        List<String> airportCodes13 = new ArrayList<>();
        airportCodes13.add("BNA");
        destinations.add(
                new Destination("Nashville" /*city*/,
                        "Tennessee" /*state*/,
                        "United States" /*country*/,
                        airportCodes13,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        0 /*beach*/,
                        5 /*bigCity*/,
                        3 /*historic*/,
                        3 /*outdoorActivities*/,
                        3 /*familyFriendly*/,
                        5 /*entertainment*/,
                        5 /*casinos*/,
                        5 /*greatDining*/,
                        0 /*warmDestination*/,
                        2 /*romantic*/,
                        0 /*cruises*/));

//      Los Angeles, California
        List<String> airportCodes14 = new ArrayList<>();
        airportCodes14.add("LAX");
        destinations.add(
                new Destination("Los Angeles" /*city*/,
                        "California" /*state*/,
                        "United States" /*country*/,
                        airportCodes14,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        8 /*beach*/,
                        8 /*bigCity*/,
                        0 /*historic*/,
                        7 /*outdoorActivities*/,
                        7 /*familyFriendly*/,
                        7 /*entertainment*/,
                        5 /*casinos*/,
                        7 /*greatDining*/,
                        7 /*warmDestination*/,
                        2 /*romantic*/,
                        10 /*cruises*/));

//      San Antonio, Texas
        List<String> airportCodes15 = new ArrayList<>();
        airportCodes15.add("SAT");
        destinations.add(
                new Destination("San Antonio" /*city*/,
                        "Texas" /*state*/,
                        "United States" /*country*/,
                        airportCodes15,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        0 /*beach*/,
                        5 /*bigCity*/,
                        7 /*historic*/,
                        7 /*outdoorActivities*/,
                        7 /*familyFriendly*/,
                        5 /*entertainment*/,
                        5 /*casinos*/,
                        5 /*greatDining*/,
                        5 /*warmDestination*/,
                        0 /*romantic*/,
                        0 /*cruises*/));

//      Austin, Texas
        List<String> airportCodes16 = new ArrayList<>();
        airportCodes16.add("AUS");
        destinations.add(
                new Destination("Austin" /*city*/,
                        "Texas" /*state*/,
                        "United States" /*country*/,
                        airportCodes16,
                        3 /*price*/,
                        0 /*inUs*/,
                        0 /*inEurope*/,
                        0 /*beach*/,
                        8 /*bigCity*/,
                        0 /*historic*/,
                        5 /*outdoorActivities*/,
                        7 /*familyFriendly*/,
                        7 /*entertainment*/,
                        7 /*casinos*/,
                        7 /*greatDining*/,
                        5 /*warmDestination*/,
                        0 /*romantic*/,
                        0 /*cruises*/));

//      Marrakech, Morocco
        List<String> airportCodes17 = new ArrayList<>();
            airportCodes17.add("RAK"); // Not here
//        destinations.add(
//                new Destination("Marrakech" /*city*/,
//                        null /*state*/,
//                        "Morocco" /*country*/,
//                        airportCodes17,
//                        8 /*price*/,
//                        0 /*inUs*/,
//                        0 /*inEurope*/,
//                        0 /*beach*/,
//                        4 /*bigCity*/,
//                        8 /*historic*/,
//                        5 /*outdoorActivities*/,
//                        2 /*familyFriendly*/,
//                        5 /*entertainment*/,
//                        0 /*casinos*/,
//                        5 /*greatDining*/,
//                        6 /*warmDestination*/,
//                        0 /*romantic*/,
//                        0 /*cruises*/));

//      Prague, Czech Republic
        List<String> airportCodes18 = new ArrayList<>();
            airportCodes18.add("PRG"); // Not here
//        destinations.add(
//                new Destination("Prague" /*city*/,
//                        null /*state*/,
//                        "Czech Republic" /*country*/,
//                        airportCodes18,
//                        8 /*price*/,
//                        0 /*inUs*/,
//                        10 /*inEurope*/,
//                        0 /*beach*/,
//                        10 /*bigCity*/,
//                        8 /*historic*/,
//                        5 /*outdoorActivities*/,
//                        5 /*familyFriendly*/,
//                        10 /*entertainment*/,
//                        5 /*casinos*/,
//                        5 /*greatDining*/,
//                        0 /*warmDestination*/,
//                        3 /*romantic*/,
//                        0 /*cruises*/));

//      London, United Kingdom
        List<String> airportCodes19 = new ArrayList<>();
            airportCodes19.add("LHR"); // Not here
//        destinations.add(
//                new Destination("London" /*city*/,
//                        null /*state*/,
//                        "United Kingdom" /*country*/,
//                        airportCodes19,
//                        8 /*price*/,
//                        0 /*inUs*/,
//                        10 /*inEurope*/,
//                        0 /*beach*/,
//                        10 /*bigCity*/,
//                        10 /*historic*/,
//                        7 /*outdoorActivities*/,
//                        5 /*familyFriendly*/,
//                        10 /*entertainment*/,
//                        5 /*casinos*/,
//                        5 /*greatDining*/,
//                        0 /*warmDestination*/,
//                        3 /*romantic*/,
//                        0 /*cruises*/));

//      Rome, Italy
        List<String> airportCodes20 = new ArrayList<>();
            airportCodes20.add("FCO"); // Not here
//        destinations.add(
//                new Destination("Rome" /*city*/,
//                        null /*state*/,
//                        "Italy" /*country*/,
//                        airportCodes20,
//                        8 /*price*/,
//                        0 /*inUs*/,
//                        10 /*inEurope*/,
//                        0 /*beach*/,
//                        10 /*bigCity*/,
//                        10 /*historic*/,
//                        7 /*outdoorActivities*/,
//                        10 /*familyFriendly*/,
//                        5 /*entertainment*/,
//                        0 /*casinos*/,
//                        10 /*greatDining*/,
//                        5 /*warmDestination*/,
//                        10 /*romantic*/,
//                        7 /*cruises*/));

//      Paris, France
        List<String> airportCodes21 = new ArrayList<>();
            airportCodes21.add("CDG"); // Not here
//        destinations.add(
//                new Destination("Paris" /*city*/,
//                        null /*state*/,
//                        "France" /*country*/,
//                        airportCodes21,
//                        8 /*price*/,
//                        0 /*inUs*/,
//                        10 /*inEurope*/,
//                        0 /*beach*/,
//                        10 /*bigCity*/,
//                        7 /*historic*/,
//                        7 /*outdoorActivities*/,
//                        10 /*familyFriendly*/,
//                        10 /*entertainment*/,
//                        5 /*casinos*/,
//                        10 /*greatDining*/,
//                        1 /*warmDestination*/,
//                        10 /*romantic*/,
//                        0 /*cruises*/));

//      Zermatt, Switzerland
        List<String> airportCodes22 = new ArrayList<>();
            airportCodes22.add("CH"); // Not here
//        destinations.add(
//                new Destination("Zermatt" /*city*/,
//                        null /*state*/,
//                        "Switzerland" /*country*/,
//                        airportCodes22,
//                        8 /*price*/,
//                        0 /*inUs*/,
//                        10 /*inEurope*/,
//                        0 /*beach*/,
//                        2 /*bigCity*/,
//                        0 /*historic*/,
//                        10 /*outdoorActivities*/,
//                        4 /*familyFriendly*/,
//                        3 /*entertainment*/,
//                        5 /*casinos*/,
//                        0 /*greatDining*/,
//                        0 /*warmDestination*/,
//                        5 /*romantic*/,
//                        0 /*cruises*/));

//      Barcelona, Spain
        List<String> airportCodes23 = new ArrayList<>();
            airportCodes23.add("BCN"); // Not here
//        destinations.add(
//                new Destination("Barcelona" /*city*/,
//                        null /*state*/,
//                        "Spain" /*country*/,
//                        airportCodes23,
//                        8 /*price*/,
//                        0 /*inUs*/,
//                        10 /*inEurope*/,
//                        7 /*beach*/,
//                        5 /*bigCity*/,
//                        5 /*historic*/,
//                        8 /*outdoorActivities*/,
//                        5 /*familyFriendly*/,
//                        5 /*entertainment*/,
//                        5 /*casinos*/,
//                        5 /*greatDining*/,
//                        2 /*warmDestination*/,
//                        5 /*romantic*/,
//                        0 /*cruises*/));

//      Hong Kong, China
        List<String> airportCodes24 = new ArrayList<>();
            airportCodes24.add("HKG"); // Not here
//        destinations.add(
//                new Destination("Hong Kong" /*city*/,
//                        null /*state*/,
//                        "China" /*country*/,
//                        airportCodes24,
//                        10 /*price*/,
//                        0 /*inUs*/,
//                        0 /*inEurope*/,
//                        3 /*beach*/,
//                        10 /*bigCity*/,
//                        0 /*historic*/,
//                        5 /*outdoorActivities*/,
//                        3 /*familyFriendly*/,
//                        5 /*entertainment*/,
//                        5 /*casinos*/,
//                        7 /*greatDining*/,
//                        7 /*warmDestination*/,
//                        3 /*romantic*/,
//                        5 /*cruises*/));

//      Sydney, Australia
        List<String> airportCodes25 = new ArrayList<>();
            airportCodes25.add("SYD"); // Not here
//        destinations.add(
//                new Destination("Sydney" /*city*/,
//                        null /*state*/,
//                        "Australia" /*country*/,
//                        airportCodes25,
//                        10 /*price*/,
//                        0 /*inUs*/,
//                        0 /*inEurope*/,
//                        10 /*beach*/,
//                        8 /*bigCity*/,
//                        8 /*historic*/,
//                        8 /*outdoorActivities*/,
//                        8 /*familyFriendly*/,
//                        5 /*entertainment*/,
//                        0 /*casinos*/,
//                        5 /*greatDining*/,
//                        10 /*warmDestination*/,
//                        7 /*romantic*/,
//                        8 /*cruises*/));

        destinationMap = new HashMap<>();
        for (Destination destination : destinations){
            destinationMap.put(destination.getCity(), destination);
        }
    }

    /*

    Combined/Trimmed City List:
    --------------------------
    New York City, New York
    Charleston, South Carolina
    Las Vegas, Nevada
    Seattle, Washington
    San Francisco, California
    Washington DC, District of Columbia
    New Orleans, Louisiana
    San Diego, California
    Honolulu, Hawaii
    Miami Beach, Florida
    Boston, Massachusetts
    Orlando, Florida
    Nashville, Tennessee
    Los Angeles, California
    San Antonio, Texas
    Austin, Texas
    Marrakech, Morocco
    Prague, Czech Republic
    London, United Kingdom
    Rome, Italy
    Paris, France
    Zermatt, Switzerland
    Barcelona, Spain
    Hong Kong, China
    Sydney, Australia

    Features (Scale 1-10):
    --------------------------
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
}
