package application.service;

import application.domain.Destination;
import application.domain.Suggestion;
import application.domain.UserStore;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rellison on 12/1/15.
 */
public class DestinationSuggestionServiceTest {

    DestinationSuggestionService service;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setup(){
        service = new DestinationSuggestionService();
        service.setUserStore(new UserStore());
    }

    @Test
    public void testService(){

//        "London" /*city*/,
//        8 /*price*/,
//        0 /*inUs*/,
//        10 /*inEurope*/,
//        0 /*beach*/,
//        10 /*bigCity*/,
//        10 /*historic*/,
//        7 /*outdoorActivities*/,
//        5 /*familyFriendly*/,
//        10 /*entertainment*/,
//        5 /*casinos*/,
//        5 /*greatDining*/,
//        0 /*warmDestination*/,
//        3 /*romantic*/,
//        0 /*cruises*/

//        "Hong Kong" /*city*/,
//        10 /*price*/,
//        0 /*inUs*/,
//        0 /*inEurope*/,
//        3 /*beach*/,
//        10 /*bigCity*/,
//        0 /*historic*/,
//        5 /*outdoorActivities*/,
//        3 /*familyFriendly*/,
//        5 /*entertainment*/,
//        5 /*casinos*/,
//        7 /*greatDining*/,
//        7 /*warmDestination*/,
//        3 /*romantic*/,
//        5 /*cruises*/


        List<String> cities = new ArrayList<>();
        cities.add("London");
        cities.add("Hong Kong");

        Suggestion result = service.getNextDestination("someUser", "origin");

        log.info(result.getCityName());
    }

}