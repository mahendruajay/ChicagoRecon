application.RateRoute = Ember.Route.extend({
	
	model: function(params) {
	
		return $.ajax({
			url: "/api/suggestion/getAirportInfo",
			method: "GET",
			data: {
				lat: params.latitude,
				long: params.longitude
			},
			dataType: "json"
		}).then(function(airport) {
	
			return $.ajax({
				url: "/api/suggestion",
				method: "GET",
				data: {
					user: params.user,
					departureAirportCode: airport.code,
					departureAirportCity: airport.city,
					departureDate: "2016-02-23" // TODO
				},
				dataType: "json"
			});
		});
	},
	
	actions: {
		error: function() {
			$('#server-side-content').remove();
			return true;
		},
		didTransition: function() {
			$('#server-side-content').remove();
			return true;
		}
	}
});

application.RateController = Ember.Controller.extend({
	queryParams: [
		"user",
		{ latitude: "lat" },
		{ longitude: "long" }
	],
	
	user: null,
	latitude: null,
	longitude: null
});