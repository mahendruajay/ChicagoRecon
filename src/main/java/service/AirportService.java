package service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by rellison on 11/30/15.
 */
@Component
public class AirportService {
    public String getAirportCode(Double latitude, Double longitude) {
        return "ORD";
    }
}
