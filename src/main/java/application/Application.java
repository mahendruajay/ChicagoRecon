package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

//        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
//
//        String departureDate = "2015-12-24";
//        LocalDate depDate = LocalDate.parse(departureDate, DATE_FORMAT);
//        LocalDate returnDate = depDate.plusDays(7);
//
//        String retDate = returnDate.format(DATE_FORMAT);
//
//        FlightSearchService flightSearchService = (FlightSearchService) ctx.getBean("flightSearchService");
//
//        List<Destination> destinations = new Destinations().getDestinations();
//
//        for (Destination destination : destinations) {
//
//            String airportCode = destination.getAirportCodes().get(0);
//
//            System.out.println("Getting best flight price for destination: " + airportCode + "," + destination.getCity());
//
//            flightSearchService.getFlights(departureDate, "ORD", airportCode, retDate);
//
//            System.out.println("Finished processing best flight price for destination: " + airportCode + "," + destination.getCity());
//
//        }
    }

}
