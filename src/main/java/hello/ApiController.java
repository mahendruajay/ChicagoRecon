package hello;

import domain.Flight;
import domain.RatedFlight;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ApiController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value="/api/suggestion/rate", method= RequestMethod.POST)
    public String rateFlight(@RequestBody RatedFlight ratedFlight) {

        return "flight rated";
    }

    @RequestMapping(value="/api/suggestion", method= RequestMethod.GET)
    public Flight getFlight(@RequestParam("user") String user,
                            @RequestParam("origin") String origin,
                            @RequestParam("date") Date date) {

        return new Flight();
    }

}
