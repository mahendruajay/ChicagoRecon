package controller;

import domain.FlightSuggestion;
import domain.RatedSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AirportService;

import java.math.BigDecimal;
import java.util.Date;

@RestController
public class ApiController {

    @Autowired
    AirportService airportService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/api/suggestion/rate", method = RequestMethod.POST)
    public String rateFlight(@RequestBody RatedSuggestion ratedSuggestion) {

        return "suggestion rated";
    }

    @RequestMapping(value = "/api/suggestion", method = RequestMethod.GET)
    public FlightSuggestion getFlight(@RequestParam("user") String user,
                            @RequestParam("origin") String origin,
                            @RequestParam("date") Date date) {

        return new FlightSuggestion(BigDecimal.valueOf(100), "2016-01-01", "CHI", "Chicago, IL", "MIA", "Miami, FL","imageUrl", null);
    }

    @RequestMapping("/api/suggestion/{latitude}/{longitude}")
    public String getAirportCode(@PathVariable("latitude") Double latitude,
                                 @PathVariable("longitude") Double longitude) {

        return airportService.getAirportCode(latitude, longitude);
    }
}
