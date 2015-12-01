package application.controller;

import application.domain.Airport;
import application.domain.DestinationDetails;
import application.domain.FlightSuggestion;
import application.domain.RatedSuggestion;
import application.service.AirportService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        DestinationDetails destinationDetails = new DestinationDetails();
        destinationDetails.setDestinationImages(Lists.newArrayList("imageUrl"));

        return new FlightSuggestion(BigDecimal.valueOf(100), "2016-01-01",
                new Airport("CHI", "Chicago, IL"), new Airport("MIA", "Miami, FL"), destinationDetails);
    }

    @RequestMapping("/api/suggestion/{latitude}/{longitude}")
    public String getAirportCode(@PathVariable("latitude") Double latitude,
                                 @PathVariable("longitude") Double longitude) {

        return airportService.getAirportCode(latitude, longitude);
    }
}
