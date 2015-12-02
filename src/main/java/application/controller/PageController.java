package application.controller;

import application.domain.Airport;
import application.domain.FlightSuggestion;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PageController {

    @RequestMapping("/rate")
    public String pageLoad() {

        // the ftl file name under resources/templates
        return "application";
    }
    
    @RequestMapping("/rate-mock")
    public String rateMock() {

        // the ftl file name under resources/templates
        return "homepage";
    }

    @RequestMapping("/wallet")
    ModelAndView wallet() {
        FlightSuggestion suggestion = new FlightSuggestion("200", "2016-01-01", new Airport("ord", "Seattle"), new Airport("mia", "Miami"), null, null);
        suggestion.getDeeplinkURL();
        FlightSuggestion suggestion2 = new FlightSuggestion("100", "2016-01-01", new Airport("ord", "Seattle"), new Airport("sea", "Seattle"), null, null);
        List<FlightSuggestion> suggestions = Lists.newArrayList(suggestion, suggestion2);
        return new ModelAndView("walletpage", "suggestions", suggestions);
    }

}