package application.service;

import application.domain.Flights;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by anushasura on 12/1/15.
 */

@Component
public class FlightSearchService {

    public static final String BASE_URL = "http://terminal2.expedia.com/x/mflights/search?";
    public static final String API_KEY = "ZfO3QR30jRKFZQaAhTAN85XrWxpe2dyB";

    @Autowired
    private ServiceCache serviceCache;

    public Flights getFlights(String departureDate, String originAirport, String destinationAirport,
                              String returnDate) {
        String key = serviceCache.generateFlightsCacheKey(departureDate, originAirport, destinationAirport, returnDate);
        Flights flights = serviceCache.getFlights(key);

        if (flights != null) {
            return flights;
        }

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

        flights = new Flights("2015-12-28", "LAS", "SEA", "2015-12-30", legId, price);

        serviceCache.cacheFlights(key, flights);

        return flights;

    }

    public String parseJson(String entity) {
        ArrayList<String> priceList = new ArrayList<String>();
        HashMap<String, TreeMap<Integer,ArrayList<String>>> m = new HashMap<String, TreeMap<Integer,
                ArrayList<String>>>();
        JsonElement jelement = new JsonParser().parse(entity);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("offers");

        for(int i = 0, size = jarray.size(); i < size; i++) {
            jobject = jarray.get(i).getAsJsonObject();
            String price = jobject.get("totalFare").toString();
            priceList.add(price);
            String legIds = jobject.getAsJsonArray("legIds").toString();
            int NumberofLegIds = jobject.getAsJsonArray("legIds").size();

            if (m.containsKey(price)) {
                TreeMap<Integer,ArrayList<String>> n = m.get(price);

                if(n.containsKey(NumberofLegIds)) {
                    ArrayList<String> lIds = n.get(NumberofLegIds);
                    lIds.add(legIds);
                    n.put(NumberofLegIds, lIds);
                }

                else {
                    ArrayList<String> newlIds = new ArrayList<String>();
                    newlIds.add(legIds);
                    n.put(NumberofLegIds, newlIds);
                }

                m.put(price,n);

            } else {
                TreeMap<Integer,ArrayList<String>> newValue = new TreeMap<Integer,ArrayList<String>>();
                ArrayList<String> lIds = new ArrayList<String>();
                lIds.add(legIds);
                newValue.put(NumberofLegIds, lIds);
                m.put(price, newValue);
            }
        }

        Collections.sort(priceList);

        String lowestPrice = priceList.get(0);
        TreeMap<Integer,ArrayList<String>> legIdList = m.get(lowestPrice);

        return legIdList.firstEntry().getValue().get(0) + "!" +lowestPrice;
    }
}


