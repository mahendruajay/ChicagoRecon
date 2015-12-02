package application.service;

import application.domain.Airport;
import application.domain.CruiseSuggestion;
import application.domain.Flights;
import com.google.common.base.Joiner;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by predding on 12/1/15.
 */
@Component
public class ServiceCache {
    private static Map<String, Flights> flightsMap = new HashMap<>();
    private static Map<String, Airport> airportMap = new HashMap<>();
    private static Map<String, CruiseSuggestion> cruiseSuggestionMap = new HashMap<>();

    public Flights getFlights(String key) {
        return flightsMap.get(key);
    }

    public void cacheFlights(String key, Flights flights) {
        flightsMap.put(key, flights);
    }

    public String generateFlightsCacheKey(String departureDate, String originAirport, String destinationAirport, String returnDate) {
        return Joiner.on(",").join(Lists.newArrayList(departureDate, originAirport, destinationAirport, returnDate));
    }

    public Airport getAirport(String key) {
        return airportMap.get(key);
    }

    public void cacheAirport(String key, Airport airport) {
        airportMap.put(key, airport);
    }

    public String generateAirportCacheKey(Double latitude, Double longitude) {
        BigDecimal latitudeRounded = new BigDecimal(latitude);
        latitudeRounded = latitudeRounded.setScale(2, RoundingMode.HALF_UP);

        BigDecimal longitudeRounded = new BigDecimal(longitude);
        longitudeRounded = longitudeRounded.setScale(2, RoundingMode.HALF_UP);

        return Joiner.on(",").join(Lists.newArrayList(latitudeRounded.toString(), longitudeRounded.toString()));
    }

    public CruiseSuggestion getCruiseSuggestion(String key) {
        return cruiseSuggestionMap.get(key);
    }

    public void cacheCruiseSuggestion(String key, CruiseSuggestion cruiseSuggestion) {
        cruiseSuggestionMap.put(key, cruiseSuggestion);
    }

    public String generateCruiseSuggestionCacheKey(String destinationAirportCode, LocalDate departureDate, LocalDate returnDate) {
        List<String> keys = Lists.newArrayList(destinationAirportCode, departureDate.toString(), returnDate.toString());

        return Joiner.on(",").join(keys);
    }
}
