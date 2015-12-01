package hello;

import domain.LikedFlight;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value="/rateFlight", method= RequestMethod.POST)
    public String rateFlight(@RequestBody LikedFlight likedFlight) {

        return "flight liked";
    }



}
