package application.controller;

import application.domain.*;
import application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
public class ApiController {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;

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

    @Autowired
    DestinationSuggestionService destinationSuggestionService;

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
    public FlightSuggestion getFlight(@RequestParam("user") String userID,
                                      @RequestParam("departureCity") String departureCity,
                                      @RequestParam("departureCity") String departureAirportCode,
                                      @RequestParam("date") String date) {

        // The imageService and cruiseSuggestionService calls should happen from withing the flightSuggestionService, but for now are here
        LocalDate departureDate = LocalDate.parse(date, DATE_FORMAT);
        LocalDate returnDate = departureDate.plusDays(7);

        Suggestion suggestion = destinationSuggestionService.getNextDestination(userID, departureCity);

        String depDate = departureDate.format(DATE_FORMAT);
        String retDate = returnDate.format(DATE_FORMAT);

        Flights flights = flightSearchService.getFlights(depDate, departureAirportCode, suggestion.getAirportCodes().get(0), retDate);

        DestinationDetails destinationDetails = new DestinationDetails();
        destinationDetails.setDestinationImages(imageService.getCityImages(suggestion.getCityName(), suggestion.getLabels(), 3));

        CruiseSuggestion cruiseSuggestion = cruiseSuggestionService.getCruiseSuggestion(suggestion.getAirportCodes().get(0), departureDate, returnDate);

        return new FlightSuggestion(flights.getPrice(), flights.getDepartureDate(),
                new Airport(departureAirportCode, departureCity), new Airport(suggestion.getAirportCodes().get(0), suggestion.getCityName()), destinationDetails, cruiseSuggestion);
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

