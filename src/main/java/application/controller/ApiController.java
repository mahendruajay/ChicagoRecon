package application.controller;

import application.domain.*;
import application.service.*;
import com.google.common.collect.Lists;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ApiController {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Autowired
    private AirportService airportService;

    @Autowired
    private ImageService imageService;

    @Autowired
    FlightSearchService flightSearchService;

    @Autowired
    UserStoreService userStoreService;

    @Autowired
    private CruiseSuggestionService cruiseSuggestionService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/api/suggestion/rate", method = RequestMethod.POST)
    public String rateFlight(@RequestBody RatedSuggestion ratedSuggestion) {

        userStoreService.storeUserResponse(ratedSuggestion);
        return "Success";
    }

    @RequestMapping(value = "/api/suggestion", method = RequestMethod.GET)
    public FlightSuggestion getFlight(@RequestParam("user") String user,
                                      @RequestParam("departureCity") String departureCity,
                                      @RequestParam("date") String date) {

        // The imageService and cruiseSuggestionService calls should happen from withing the flightSuggestionService, but for now are here
        LocalDate departureDate = LocalDate.parse(date, DATE_FORMAT);
        LocalDate returnDate = departureDate.plusDays(7);

        DestinationDetails destinationDetails = new DestinationDetails();
        destinationDetails.setDestinationImages(imageService.getCityImages("Miami", Lists.newArrayList("beach"), 3));

        CruiseSuggestion cruiseSuggestion = cruiseSuggestionService.getCruiseSuggestion("Miami", departureDate, returnDate);

        return new FlightSuggestion(BigDecimal.valueOf(100), "2016-01-01",
                new Airport("CHI", "Chicago, IL"), new Airport("MIA", "Miami, FL"), destinationDetails, cruiseSuggestion);
    }

    @RequestMapping("/api/suggestion/getAirportInfo")
    public Airport getAirportCode(@RequestParam("lat") String latitude,
                                  @RequestParam("long") String longitude) {

        Double lat = Double.parseDouble(latitude);
        Double longt = Double.parseDouble(longitude);

        return airportService.getAirportCode(lat, longt);
    }

    @RequestMapping(value = "/api/flightSearch", method = RequestMethod.GET)
    public Flights getFlights(@RequestParam("departureDate") String departureDate,
                              @RequestParam("departureAirport") String departureAirport,
                              @RequestParam("arrivalAirport") String arrivalAirport,
                              @RequestParam("returnDate") String returnDate
                              ) {

        return flightSearchService.getFlights(departureDate, departureAirport, arrivalAirport, returnDate);

    }
}

