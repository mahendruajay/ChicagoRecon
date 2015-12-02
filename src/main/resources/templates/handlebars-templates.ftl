<script type="text/x-handlebars" data-template-name="application">
	{{outlet}}
</script>

<script type="text/x-handlebars" data-template-name="rate">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<!--start-->
				<div id="carousel-example-generic"  class="carousel slide">
				
					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						{{#each suggestions as |suggestion|}}
							<div id="suggestion-{{suggestion.id}}" class="item {{if suggestion.active "active"}}">
								<img src="/static/assets/{{suggestion.destinationAirport.code}}.jpg" alt="">
								<div class="carousel-caption">
									<h3>{{suggestion.destinationAirport.city}}</h3>
									<h4>{{suggestion.displayDepartureDate}} - {{suggestion.displayReturnDate}}</h4>
									<h3>${r"$"}{{suggestion.price}}</h3>
									<p><a class="btn btn-warning" href="/api/flight/search?startDate={{suggestion.departureDate}}&returnDate={{suggestion.returnDate}}&FromAirport={{suggestion.originAirport.code}}&&ToAirport={{suggestion.destinationAirport.code}}">Book</a></p>
								</div>
							</div>
						{{/each}}
					</div>
					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev" {{action 'dislike'}}>
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						<span class="sr-only">Dislike</span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next" {{action 'like'}}>
						<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
						<span class="sr-only">Heart</span>
					</a>
				</div>
				<!--end-->
			</div>
		</div>
	</div>
	
</script>