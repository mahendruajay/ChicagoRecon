<script type="text/x-handlebars" data-template-name="application">
	{{outlet}}
</script>

<script type="text/x-handlebars" data-template-name="rate">
	<a class="btn btn-success" href="/wallet?user={{model.user}}">View Wallet</a>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<!--start-->
				<div id="carousel-example-generic"  class="carousel slide">
				
					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						{{#each suggestions as |suggestion|}}
							<div id="suggestion-{{suggestion.id}}" class="item {{if suggestion.active "active"}}">
								<img class="background" src="/static/assets/{{suggestion.destinationAirport.code}}.jpg" alt="{{suggestion.destinationAirport.city}}">
								<div class="bottom-overlay">
								</div>
								<div class="carousel-caption">
									<h2>{{suggestion.destinationAirport.city}}</h2>
									<h4>{{suggestion.displayDepartureDate}} - {{suggestion.displayReturnDate}}</h4>
									<h2>${r"$"}{{suggestion.price}}</h2>
									<div class="book-and-explore">
										<div class="book-inner">
											<a target="_blank" class="btn btn-warning" href="/api/flight/search?startDate={{suggestion.departureDate}}&returnDate={{suggestion.returnDate}}&FromAirport={{suggestion.originAirport.code}}&&ToAirport={{suggestion.destinationAirport.code}}">Book</a>
										</div>
										<div class="explore-inner">
											{{#if suggestion.cruiseSuggestion}}
												<span class="explore">
													<a target="_blank" href="{{suggestion.cruiseSuggestion.cruiseSearchDeepLink}}">Explore Cruise deals departing from {{suggestion.destinationAirport.city}}</a>
												</span>
											{{/if}}
										</div>
									</div>
								</div>
							</div>
						{{/each}}
					</div>
					<!-- Controls -->
					<div class="icon-overlay">
						<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev" {{action 'dislike'}}>
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							<span class="sr-only">Dislike</span>
						</a>
						<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next" {{action 'like'}}>
							<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
							<span class="sr-only">Heart</span>
						</a>
					</div>
				</div>
				<!--end-->
			</div>
		</div>
	</div>
	
</script>