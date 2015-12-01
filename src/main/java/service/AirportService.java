package service;

import org.springframework.stereotype.Component;

/**
 * Created by rellison on 11/30/15.
 */
@Component
public class AirportService {
    public String getAirportCode(Double latitude, Double longitude) {
        return "ORD";
    }
}
