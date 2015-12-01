package application.domain;

/**
 * Created by anushasura on 12/1/15.
 */
public class Flights {

        private String departureDate;
        private String originAirport;
        private String destinationAirport;
        private String returnDate;
        private String legId;
        private String price;

        public Flights(String departureDate, String originAirport, String
                destinationAirport, String returnDate, String legId, String price) {
            this.departureDate = departureDate;
            this.originAirport = originAirport;
            this.destinationAirport = destinationAirport;
            this.returnDate = returnDate;
            this.legId = legId;
            this.price = price;
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

        public String getLegId() { return legId; }

        public String getPrice() { return price; }

}
