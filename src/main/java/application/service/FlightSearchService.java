package application.service;

import application.domain.Flights;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by anushasura on 12/1/15.
 */

@Component
public class FlightSearchService {

    public static final String BASE_URL = "http://terminal2.expedia.com/x/mflights/search?";
    public static final String API_KEY = "ZfO3QR30jRKFZQaAhTAN85XrWxpe2dyB";

    public Flights getFlights(String departureDate, String originAirport, String destinationAirport,
                                       String returnDate) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URL).
                queryParam("departureDate", departureDate).
                queryParam("departureAirport", originAirport).
                queryParam("arrivalAirport", destinationAirport).
                queryParam("returnDate", returnDate).
                queryParam("apikey", API_KEY);

        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.get();
        String entity = response.readEntity(String.class);
        String legIdAndPrice = parseJson(entity);
        String legId = legIdAndPrice.split("!")[0];
        String price = legIdAndPrice.split("!")[1];

        Flights flights = new Flights("2015-12-28", "LAS", "SEA", "2015-12-30", legId, price);
        return flights;

    }

    public String parseJson(String entity) {
        ArrayList<String> priceList = new ArrayList<String>();
        HashMap<String, ArrayList<String>> m = new HashMap<String,ArrayList<String>>();
        JsonElement jelement = new JsonParser().parse(entity);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("offers");

        for(int i = 0, size = jarray.size(); i < size; i++) {
            jobject = jarray.get(i).getAsJsonObject();
            String price = jobject.get("totalFare").toString();
            priceList.add(price);
            String legIds = jobject.getAsJsonArray("legIds").toString();


            if (m.containsKey(price)) {
                ArrayList<String> value = m.get(price);
                value.add(legIds);
                m.put(price, value);
            } else {
                ArrayList<String> newValue = new ArrayList<String>();
                newValue.add(legIds);
                m.put(price, newValue);
            }
        }

        Collections.sort(priceList);

        String lowestPrice = priceList.get(0);

        ArrayList<String> legIdList = m.get(lowestPrice);

        return legIdList.get(0) + "!" +lowestPrice;
    }
}


