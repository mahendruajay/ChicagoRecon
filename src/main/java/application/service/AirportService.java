package application.service;

import application.domain.Airport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by rellison on 11/30/15.
 */
@Component
public class AirportService {

    private static final String GAIA_GET_FEATURES_BY_LAT_LONG = "http://terminal2.expedia.com/x/geo/features";
    private static final String GAIA_GET_FEATURES_BY_ID = "http://terminal2.expedia.com/x/geo/features";
    private static final String APIKEY = "ZfO3QR30jRKFZQaAhTAN85XrWxpe2dyB";


    public Airport getAirportCode(Double latitude, Double longitude) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(GAIA_GET_FEATURES_BY_LAT_LONG).
                queryParam("within", "0km").
                queryParam("lat", latitude.toString()).
                queryParam("lng", longitude.toString()).
//                queryParam("cid", "ReconHackathon").
//                queryParam("apk", "HackathonDemo").
                queryParam("type", "city").
                queryParam("verbose", "3").
                queryParam("apikey", APIKEY);

        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.get();
        System.out.println("" + response.getStatus());
        String entity = response.readEntity(String.class);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
//        gson.fromJson(entity);

        // TODO: AJ: Read Json response, read links->common->hasPrimaryTla->featureID for featureType

        String featureID = "4277587";

        client = ClientBuilder.newClient();
        target = client.target(GAIA_GET_FEATURES_BY_ID).
                path(featureID).
//                queryParam("cid", "ReconHackathon").
//                queryParam("apk", "HackathonDemo").
        queryParam("verbose", "3").
                queryParam("apikey", APIKEY);

        invocationBuilder = target.request();
        response = invocationBuilder.get();
        System.out.println("" + response.getStatus());
        entity = response.readEntity(String.class);

        // TODO: AJ: Read Json response, read name
        // read tags->iata->airportCode->value(SEA)


        Airport airport = new Airport("ORD", "Chicago");

        return airport;

    }
}
