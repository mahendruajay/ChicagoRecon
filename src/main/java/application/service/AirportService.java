package application.service;

import application.domain.Airport;
import org.json.JSONArray;
import org.json.JSONObject;
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
                queryParam("type", "city").
                queryParam("verbose", "3").
                queryParam("apikey", APIKEY);

        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.get();
        System.out.println("" + response.getStatus());
        String entity = response.readEntity(String.class);

        JSONArray featuresRadialArray = new JSONArray(entity);

        JSONObject featureRadialObject = (JSONObject) featuresRadialArray.get(0);

        String city = "";
        String longCity = (String) featureRadialObject.get("name");
        String[] cityName = longCity.split(",");

        if (cityName.length > 0) {
            city = cityName[0];
        }


        JSONObject links = (JSONObject) featureRadialObject.get("links");
        String featureID = "";

        if (null != links) {
            featureID = findPrimaryAirportFeatureID(links);
        }

        if (featureID.equalsIgnoreCase("")) {
            return new Airport("ORD", "Chicago");
        }

//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        gson.fromJson(entity);

        // TODO: AJ: Read Json response, read links->common->hasPrimaryTla->featureID for featureType

        client = ClientBuilder.newClient();
        target = client.target(GAIA_GET_FEATURES_BY_ID).
                path(featureID).
                queryParam("verbose", "3").
                queryParam("apikey", APIKEY);

        invocationBuilder = target.request();
        response = invocationBuilder.get();
        System.out.println("" + response.getStatus());
        entity = response.readEntity(String.class);


        JSONObject features = new JSONObject(entity);

        String airportCode = findAirportCode(features);
        if (airportCode.equalsIgnoreCase("")) {
            return new Airport("ORD", "Chicago");
        }

        Airport airport = new Airport(airportCode, city);

        return airport;

    }

    private String findAirportCode(JSONObject features) {
        String airportCode = "";

        if (null != features.get("tags")) {
            JSONObject tags = (JSONObject) features.get("tags");
            if (null != tags.get("iata")) {
                JSONObject iata = (JSONObject) tags.get("iata");
                if (null != iata.get("airportCode")) {
                    JSONObject airportCodeObj = (JSONObject) iata.get("airportCode");
                    if (null != airportCodeObj.get("value")) {
                        return (String) airportCodeObj.get("value");
                    }

                }
            }
        }
        return airportCode;
    }

    private String findPrimaryAirportFeatureID(JSONObject links) {
        String featureID = "";

        if (null != links.get("common")) {
            JSONArray common = (JSONArray) links.get("common");
            for (Object obj : common) {
                if (null != obj) {
                    JSONObject commonObj = (JSONObject) obj;

                    if (null != commonObj.get("hasPrimaryTla")) {
                        JSONObject hasPrimaryTla = (JSONObject) commonObj.get("hasPrimaryTla");
                        if (null != hasPrimaryTla.get("featureType")) {
                            String featureType = (String) hasPrimaryTla.get("featureType");
                            if (featureType.equalsIgnoreCase("airport")) {
                                if (null != hasPrimaryTla.get("featureId")) {
                                    return (String) hasPrimaryTla.get("featureId");
                                }
                            }

                        }
                    }
                }
            }
        }


        return featureID;
    }
}
