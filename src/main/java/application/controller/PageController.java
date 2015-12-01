package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/rate")
    public String pageLoad() {

        // the ftl file name under resources/templates
        return "application";
    }

    @RequestMapping("/wallet")
    String wallet() {

        // the ftl file name under resources/templates
        return "walletpage";
    }

}