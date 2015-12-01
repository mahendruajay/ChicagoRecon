package application.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Component;

/**
 * Created by rellison on 11/30/15.
 */
@Component
public class AirportService {

    public String getAirportCode(Double latitude, Double longitude) {

        Client client = Client.create();
        WebResource webResource = client.resource("");
        return "ORD";
    }
}
