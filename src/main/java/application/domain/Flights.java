package application.domain;

/**
 * Created by anushasura on 12/1/15.
 */
public class Flights {

        private String departureDate;
        private String originAirport;
        private String destinationAirport;
        private String returnDate;

        public Flights(String departureDate, String originAirport, String
                destinationAirport, String returnDate) {
            this.departureDate = departureDate;
            this.originAirport = originAirport;
            this.destinationAirport = destinationAirport;
            this.returnDate = returnDate;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public String getOriginAirport() {
            return originAirport;
        }

        public String getDestinationAirport() {
            return destinationAirport;
        }

        public String getReturnDate() { return returnDate; }


}
