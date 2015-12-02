
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
			cityName: suggestion.destinationAirport.city,
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
		var suggestion = this.get('model.suggestion');
		suggestion.active = true;
		suggestion.id = this.nextId;
		this.nextId++;
	
		return [suggestion];
	}.property('model.suggestion'),
	
	preLoadNextSuggestion: function() {
		var controller = this;
	
		service.getSuggestion(this.get('model.user'), this.get('model.airport'))
		.then(function(newSuggestion) {
			var suggestions = controller.get('suggestions');
			
			var prevSuggestion = $.extend({id: controller.nextId}, newSuggestion);
			controller.nextId++;
			var nextSuggestion = $.extend({id: controller.nextId}, newSuggestion);
			controller.nextId++;
			
			controller.set('suggestions', [prevSuggestion, suggestions[0], nextSuggestion]);
		});
	}.observes('model.airport'),
	
	initCarousel: function() {
		Ember.run.scheduleOnce("afterRender", function() {
			$('.carousel').carousel({ interval: false });
		});
	}.on('init')
});