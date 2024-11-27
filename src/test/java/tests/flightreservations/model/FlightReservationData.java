package tests.flightreservations.model;

public record FlightReservationData(String firstName,
		String lastName,
		String email,
		String password,
		String street,
		String city,
		String zip,
		String passengersCount,
		String expectedPrice) {
}
