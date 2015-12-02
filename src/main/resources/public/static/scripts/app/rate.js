
var service = service || {};

service.getSuggestion = function(user, airport) {
	return $.ajax({
		url: "/api/suggestion",
		method: "GET",
		data: {
			user: user,
			departureAirportCode: airport.code,
			departureAirportCity: airport.city,
			departureDate: "2016-02-23" // TODO
		},
		dataType: "json"
	});
};

service.rateSuggestion = function(user, suggestion, liked) {
	return $.ajax({
		url: "/api/suggestion/rate",
		method: "POST",
		contentType: 'application/json',
		data: JSON.stringify({
			userId: user,
			arrivalCity: suggestion.destinationAirport.city,
			departureCity: suggestion.originAirport.city,
			departureDate: suggestion.departureDate,
			arrivalDate: suggestion.returnDate,
			liked: liked,
			price: suggestion.price
		}),
		dataType: "json"
	});
};


application.RateRoute = Ember.Route.extend({
	
	model: function(params) {
	
		var route = this;
	
		return $.ajax({
			url: "/api/suggestion/getAirportInfo",
			method: "GET",
			data: {
				lat: params.latitude,
				long: params.longitude
			},
			dataType: "json"
		}).then(function(airport) {
	
			return service.getSuggestion(params.user, airport).then(function(suggestion) {
				return {
					user: params.user,
					suggestion: suggestion,
					airport: airport
				};
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
	longitude: null,
	
	nextId: 0,
	
	actions: {
		like: function() {
			var controller = this;
			
			var suggestions = controller.get('suggestions');
			if (suggestions.length < 3)
				return; // TODO
			service.rateSuggestion(this.get('model.user'), suggestions[1], true);
		
			setTimeout(function() {
				var suggestions = controller.get('suggestions');
				var newSuggestion = suggestions[2];
				newSuggestion.active=true;
				controller.set('suggestions', [newSuggestion]);
				
				controller.preLoadNextSuggestion();
			}, 1000);
		},
		dislike: function() {
			var controller = this;
		
			var suggestions = controller.get('suggestions');
			if (suggestions.length < 3)
				return; // TODO
			service.rateSuggestion(this.get('model.user'), suggestions[1], false);
		
			setTimeout(function() {
				var suggestions = controller.get('suggestions');
				var newSuggestion = suggestions[0];
				newSuggestion.active=true;
				controller.set('suggestions', [newSuggestion]);
				
				controller.preLoadNextSuggestion();
			}, 1000);
		}
	},
	
	suggestions: function() {
		var suggestions = this.get('model.suggestion');
		suggestions.suggested.active = true;
	
		return [
			suggestions.followingIfDisliked, 
			suggestions.suggested, 
			suggestions.followingIfLiked
		];
	}.property('model.suggestion'),
	
	preLoadNextSuggestion: function() {
		var controller = this;
	
		service.getSuggestion(this.get('model.user'), this.get('model.airport'))
		.then(function(newSuggestions) {
			var suggestions = controller.get('suggestions');
			
			controller.set('suggestions', [
				newSuggestions.followingIfDisliked, 
				suggestions[0], 
				newSuggestions.followingIfLiked
			]);
		});
	},
	
	initCarousel: function() {
		Ember.run.scheduleOnce("afterRender", function() {
			$('.carousel').carousel({ interval: false });
		});
	}.on('init')
});