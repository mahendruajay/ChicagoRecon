
var application = Ember.Application.extend({
	rootElement: '#client-side-content'
}).create();

application.Router.reopen({
	location: "history"
});

application.Router.map(function() {
	this.route('rate');
	this.route('details');
	this.route('wallet');
});

application.RateRoute = Ember.Route.extend({
	
	model: function() {
		return $.ajax({
			url: "/api/suggestion?user=me&origin=here&date=now",
			method: "GET",
			data: {
				user: "me",
				origin: "here",
				date: "now"
			},
			dataType: "json"
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
	
});

