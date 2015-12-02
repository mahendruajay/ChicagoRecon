package application.service;

import application.domain.CruiseSuggestion;
import com.google.common.base.Joiner;
import jersey.repackaged.com.google.common.collect.Lists;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by predding on 12/1/15.
 */
@Component
public class ServiceCache {
    private static Map<String, CruiseSuggestion> cruiseSuggestionMap;

    public CruiseSuggestion getCruiseSuggestion(String key) {
        return cruiseSuggestionMap.get(key);
    }

    public void cacheCruiseSuggestion(String key, CruiseSuggestion cruiseSuggestion) {
        cruiseSuggestionMap.put(key, cruiseSuggestion);
    }

    public String getCruiseSuggestionKey(String destinationAirportCode, LocalDate departureDate, LocalDate returnDate) {
        List<String> keys = Lists.newArrayList(destinationAirportCode, departureDate.toString(), returnDate.toString());

        return Joiner.on(",").join(keys);
    }
}
