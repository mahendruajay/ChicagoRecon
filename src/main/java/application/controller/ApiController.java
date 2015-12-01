package application.controller;

import application.domain.*;
import application.service.AirportService;
import application.service.FlightSearchService;
import application.service.ImageService;
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
                                      @RequestParam("departureCity") String departureCity,
                                      @RequestParam("date") String date) {


        DestinationDetails destinationDetails = new DestinationDetails();
        destinationDetails.setDestinationImages(imageService.getCityImages(departureCity, Lists.newArrayList("the bean"), 3));

        return new FlightSuggestion(BigDecimal.valueOf(100), "2016-01-01",
                new Airport("CHI", "Chicago, IL"), new Airport("MIA", "Miami, FL"), destinationDetails);
    }

    @RequestMapping("/api/suggestion/getAirportInfo")
    public Airport getAirportCode(@RequestParam("lat") String latitude,
                                  @RequestParam("long") String longitude) {

        Double lat = Double.parseDouble(latitude);
        Double longt = Double.parseDouble(longitude);

        return airportService.getAirportCode(lat, longt);
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

