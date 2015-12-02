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
								<img src="http://farm6.static.flickr.com/5656/23421485796_0fe17b6279.jpg" alt="">
								<div class="carousel-caption">
									<h3>{{suggestion.destinationAirport.city}}</h3>
									<p><a href="#">Flight Link</a></p>
								</div>
							</div>
						{{/each}}
					</div>
					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						<span class="sr-only">Dislike</span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
						<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
						<span class="sr-only">Heart</span>
					</a>
				</div>
				<!--end-->
			</div>
		</div>
	</div>
	
</script>