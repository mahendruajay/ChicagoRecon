package application.service;

import application.domain.Destinations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rellison on 12/1/15.
 */
@Component
public class DestinationSuggestionService {

    private Destinations destinations;

    public DestinationSuggestionService(){
        destinations = new Destinations();
    }

    public String getNextDestination(String user, String origin){
        return determineNextDestination(user, origin);
    }

    private String determineNextDestination(String user, String origin){

        //for now, always say London
        return destinations.getDestinationByName("London").getCity();
    }
}
