package application.service;

import application.domain.CruiseSuggestion;
import application.util.CommonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by predding on 12/1/15.
 */
@Component
public class CruiseSuggestionService {
    private static final String CRUISE_SEARCH_OPTIONS_URL = "https://www.expedia.com/cruiseSearchCriteria/getSearchOptions?earliest-departure-date=%s&latest-departure-date=%s&departure-port=%s&min-length=1&max-length=%d";
    private static final String CRUISE_SEARCH_DEEP_LINK_URL = "https://www.expedia.com/Cruise-Search?earliest-departure-date=%s&latest-departure-date=%s&departure-port=%s&min-length=1&max-length=%d";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;

    private static Map<String, String> airportCodeToDeparturePortMap = null;

    public CruiseSuggestion getCruiseSuggestion(String city, LocalDate departureDate, LocalDate returnDate) {
        LocalDate earliestDepartureDate = departureDate.plusDays(1);
        LocalDate latestDepartureDate = returnDate.minusDays(2);
        String earliestDeparture = earliestDepartureDate.format(DATE_FORMAT);
        String latestDeparture = latestDepartureDate.format(DATE_FORMAT);

        try {
            String departurePort = getDeparturePort(city);
            if (departurePort == null) {
                return null;
            }

            int maxLength = Period.between(earliestDepartureDate, latestDepartureDate).getDays();

            String searchOptionsUrl = String.format(Locale.ENGLISH, CRUISE_SEARCH_OPTIONS_URL, earliestDeparture, latestDeparture, departurePort, maxLength);
            String response = CommonUtils.readJsonFromUrl(searchOptionsUrl);
            JSONObject responseObject = new JSONObject(response);
            JSONArray destinationArray = responseObject.getJSONArray("destinations");

            if (destinationArray.length() > 0) {
                List<String> destinations = new ArrayList<>();
                for (int i = 0; i < destinationArray.length(); ++i) {
                    JSONObject destinationObject = destinationArray.getJSONObject(i);
                    destinations.add(destinationObject.getString("optionText"));
                }

                String deepLink = String.format(Locale.ENGLISH, CRUISE_SEARCH_DEEP_LINK_URL, earliestDeparture, latestDeparture, departurePort, maxLength);

                return new CruiseSuggestion(deepLink, destinations);
            }
        } catch (IOException e) {
            return null;
        }

        return null;
    }

    private String getDeparturePort(String city) throws IOException {
        if (airportCodeToDeparturePortMap == null) {
            InputStream inputStream = this.getClass().getResourceAsStream("/data/cruisedepartureportmappings.json");
            ObjectMapper mapper = new ObjectMapper();
            airportCodeToDeparturePortMap = mapper.readValue(inputStream, new TypeReference<Map<String, String>>() {
            });
        }

        return airportCodeToDeparturePortMap.get(city);
    }
}
