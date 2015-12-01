package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/app")
    String home() {

        // the ftl file name under resources/templates
        return "homepage";
    }

    @RequestMapping("/wallet")
    String wallet() {

        // the ftl file name under resources/templates
        return "walletpage";
    }
}