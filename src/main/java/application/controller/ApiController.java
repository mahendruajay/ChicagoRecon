package application.controller;

import application.domain.Airport;
import application.domain.DestinationDetails;
import application.domain.FlightSuggestion;
import application.domain.RatedSuggestion;
import application.domain.Flights;
import application.service.AirportService;
<<<<<<< HEAD
import application.service.ImageService;
=======
import application.service.FlightSearchService;
>>>>>>> 8b6c834... Flight search
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ApiController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private ImageService imageService;

    @Autowired
    FlightSearchService flightSearchService;

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
                                      @RequestParam("date") String date) {

        DestinationDetails destinationDetails = new DestinationDetails();
        destinationDetails.setDestinationImages(imageService.getCityImages("Chicago", Lists.newArrayList("the bean"), 3));

        return new FlightSuggestion(BigDecimal.valueOf(100), "2016-01-01",
                new Airport("CHI", "Chicago, IL"), new Airport("MIA", "Miami, FL"), destinationDetails);
    }

    @RequestMapping("/api/suggestion/{latitude}/{longitude}")
    public Airport getAirportCode(@PathVariable("latitude") Double latitude,
                                 @PathVariable("longitude") Double longitude) {

        return airportService.getAirportCode(latitude, longitude);
    }

    @RequestMapping(value = "/api/flightsuggestions", method = RequestMethod.GET)
    public Flights getFlights(@RequestParam("departureDate") String departureDate,
                              @RequestParam("departureAirport") String departureAirport,
                              @RequestParam("arrivalAirport") String arrivalAirport,
                              @RequestParam("returnDate") String returnDate
                              ) {

        return flightSearchService.getFlights(departureDate, departureAirport, arrivalAirport, returnDate);

    }
}

