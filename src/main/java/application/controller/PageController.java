package application.controller;

import application.domain.FlightSuggestion;
import application.service.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    UserStoreService userStoreService;

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
    ModelAndView wallet(@RequestParam("user") String userID) {
        List<FlightSuggestion> userLikedHistory = userStoreService.getUserLikedHistory(userID);
        return new ModelAndView("walletpage", "suggestions", userLikedHistory);
    }
}