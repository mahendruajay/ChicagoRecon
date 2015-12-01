package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/api/suggestion/{latitude}/longitude")
    public String getAirportCode() {
        return "Greetings from Spring Boot!";
    }


}
