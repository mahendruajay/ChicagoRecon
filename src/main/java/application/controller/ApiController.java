package application.controller;

import application.domain.*;
import application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM. dd");

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
    public Map<String, FlightSuggestion> getFlight(@RequestParam("user") String userID,
                                      @RequestParam("departureAirportCode") String departureAirportCode,
                                      @RequestParam("departureAirportCity") String departureAirportCity,
                                      @RequestParam("departureDate") String date) {

        // The imageService and cruiseSuggestionService calls should happen from withing the flightSuggestionService, but for now are here
        LocalDate departureDate = LocalDate.parse(date, DATE_FORMAT);
        LocalDate returnDate = departureDate.plusDays(7);

        String[] strArray = {"suggested", "followingIfLiked", "followingIfDisliked"};

        Map<String, FlightSuggestion> flightSuggestions = new LinkedHashMap<>();


        String depDate = departureDate.format(DATE_FORMAT);
        String retDate = returnDate.format(DATE_FORMAT);
        String displayDepDate = departureDate.format(DISPLAY_DATE_FORMAT);
        String displayRetDate = returnDate.format(DISPLAY_DATE_FORMAT);

        Map<String, Suggestion> suggestions = destinationSuggestionService.getNextDestination(userID, departureAirportCity);

        for (String key : suggestions.keySet()) {
            Suggestion suggestion = suggestions.get(key);

            Flights flights = flightSearchService.getFlights(depDate, departureAirportCode, suggestion.getAirportCodes().get(0), retDate);

            DestinationDetails destinationDetails = new DestinationDetails();
//            destinationDetails.setDestinationImages(imageService.getCityImages(suggestionCurrent.getCityName(), suggestionCurrent.getLabels(), 3));

            CruiseSuggestion cruiseSuggestion = cruiseSuggestionService.getCruiseSuggestion(suggestion.getAirportCodes().get(0), departureDate, returnDate);

            FlightSuggestion flightSuggestion = new FlightSuggestion(flights.getPrice(), flights.getDepartureDate(), flights.getReturnDate(),
                    new Airport(departureAirportCode, departureAirportCity), new Airport(suggestion.getAirportCodes().get(0),
                    suggestion.getCityName()), destinationDetails, cruiseSuggestion, displayDepDate, displayRetDate);
            flightSuggestions.put(key, flightSuggestion);
        }


        return flightSuggestions;
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


    @RequestMapping(value = "/api/cacheFlightSearch", method = RequestMethod.GET)
    public String cacheFlightSearch(@RequestParam("departureDate") String departureDate,
                                    @RequestParam("departureAirport") String departureAirport) {

        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;

        LocalDate depDate = LocalDate.parse(departureDate, DATE_FORMAT);
        LocalDate returnDate = depDate.plusDays(7);

        String retDate = returnDate.format(DATE_FORMAT);

        List<Destination> destinations = new Destinations().getDestinations();

        for (Destination destination : destinations) {

            String arrivalAirportCode = destination.getAirportCodes().get(0);

            System.out.println("Getting best flight price for destination: " + arrivalAirportCode + "," + destination.getCity());

            flightSearchService.getFlights(departureDate, departureAirport, arrivalAirportCode, retDate);

            System.out.println("Finished processing best flight price for destination: " + arrivalAirportCode + "," + destination.getCity());

        }

        return "success";

    }

    @RequestMapping(value = "/api/flight/search", method = RequestMethod.GET)
    public ModelAndView DeepLinkURL(@RequestParam("startDate") String startDate,
                                    @RequestParam("returnDate") String returnDate,
                                    @RequestParam("FromAirport") String FromAirport,
                                    @RequestParam("ToAirport") String ToAirport) {

        String url = "http://www.expedia.com/go/flight/search/roundtrip/";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url).path(startDate).path(returnDate).queryParam("FromAirport", FromAirport)
                .queryParam("ToAirport", ToAirport).queryParam("NumAdult", "1");

        String redirectUrl = target.getUri().toString();

        return new ModelAndView("redirect:" + redirectUrl);

    }


}
