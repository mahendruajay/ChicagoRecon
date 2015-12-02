
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

