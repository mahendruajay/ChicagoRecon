<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Fly Away</title>
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<style>
		.carousel-inner > .item > a > img, .carousel-inner > .item > img {
			line-height: 1;
			margin: 0 auto;
		}
		.carousel p{
			left: 37%;
			position: absolute;
			top: 67%;
		}
		.item{
			min-height: 290px;
			max-height: 500px;
		}
		.glyphicon-remove{
			color: #CC5E5C;
		}
		.carousel-control .glyphicon-remove,
		.carousel-control .glyphicon-heart,
		.carousel-control .icon-next,
		.carousel-control .icon-prev{
			color: #83a976;
			display: inline-block;
			margin-top: -10px;
			opacity: 1;
			position: absolute;
			top: 80%;
			z-index: 5;
		}
		.col-sm-12{
			padding-left: 0;
			padding-right: 0;
		}

		.carousel-caption {
			bottom: 20px;
			color: #fff;
			left: 15%;
			padding-bottom: 20px;
			position: absolute;
			right: 15%;
			text-align: center;
			text-shadow: 0 1px 2px rgba(0, 0, 0, 0.6);
			top: 5%;
			z-index: 10;
		}

		.carousel-inner > .item > img {
			height: 100%;
			object-fit: cover;
		}
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12">
			<!--start-->
			<div id="carousel-example-generic"  class="carousel slide">
				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="http://farm6.static.flickr.com/5656/23421485796_0fe17b6279.jpg" alt="">
						<div class="carousel-caption">
							<h3>Los Angeles</h3>
							<p><a class="btn btn-warning" href="https://www.expedia.com/Flights-Search?trip=roundtrip&leg1=from:Chicago,%20IL,%20United%20States%20%28ORD-O%27Hare%20Intl.%29,to:Austin,%20TX,%20United%20States%20%28AUS-Austin-Bergstrom%20Intl.%29,departure:12/31/2015TANYT&leg2=from:Austin,%20TX,%20United%20States%20%28AUS-Austin-Bergstrom%20Intl.%29,to:Chicago,%20IL,%20United%20States%20%28ORD-O%27Hare%20Intl.%29,departure:01/01/2016TANYT&passengers=children:0,adults:1,seniors:0,infantinlap:N&mode=search">Book</a></p>
						</div>
					</div>

					<div class="item">
						<img src="http://farm6.static.flickr.com/5656/23421485796_0fe17b6279.jpg" alt="">
						<div class="carousel-caption">
							<h3>Boston</h3>
							<p><a class="btn btn-warning" href="https://www.expedia.com/Flights-Search?trip=roundtrip&leg1=from:Chicago,%20IL,%20United%20States%20%28ORD-O%27Hare%20Intl.%29,to:Austin,%20TX,%20United%20States%20%28AUS-Austin-Bergstrom%20Intl.%29,departure:12/31/2015TANYT&leg2=from:Austin,%20TX,%20United%20States%20%28AUS-Austin-Bergstrom%20Intl.%29,to:Chicago,%20IL,%20United%20States%20%28ORD-O%27Hare%20Intl.%29,departure:01/01/2016TANYT&passengers=children:0,adults:1,seniors:0,infantinlap:N&mode=search">Book</a></p>
						</div>
					</div>
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
<script src="/static/scripts/common/jquery-1.11.3.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/carousel-swipe.js"></script>
<script type="text/javascript">
	$(function() {
		var entrySet = '';
		var lat = '41.881954799999995';
		var lon = '-87.64098820000001';
		var apiKey = 'e82ab8efa01b6d2707400c05f7e73a29';
		var per_page = 40;
		var flickrAPI = 'https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key='+apiKey+'&lat='+lat+'&lon='+lon+'&format=json&nojsoncallback=1&tags=flight&safe_search=1&per_page='+per_page+'';



		$.getJSON(flickrAPI)
				.done(function(data){

					//loop through the results with the following function
					$.each(data.photos.photo, function(i,item){

						//build the url of the photo in order to link to it
						var photoURL = 'http://farm' + item.farm + '.static.flickr.com/' + item.server + '/' + item.id + '_' + item.secret + '.jpg';
						//turn the photo id into a variable
						var photoTitle = item.title;
						var itemStorage = '<div class="item"><img src="'+photoURL+'" alt="'+photoTitle+'" class="rsImg" title="'+photoTitle+'" />' +
								'<div class="carousel-caption">'+
								'<h3>Chicago</h3>'+
								'<p><a class="btn btn-warning" href="https://www.expedia.com/Flights-Search?trip=roundtrip&leg1=from:Chicago,%20IL,%20United%20States%20%28ORD-O%27Hare%20Intl.%29,to:Austin,%20TX,%20United%20States%20%28AUS-Austin-Bergstrom%20Intl.%29,departure:12/31/2015TANYT&leg2=from:Austin,%20TX,%20United%20States%20%28AUS-Austin-Bergstrom%20Intl.%29,to:Chicago,%20IL,%20United%20States%20%28ORD-O%27Hare%20Intl.%29,departure:01/01/2016TANYT&passengers=children:0,adults:1,seniors:0,infantinlap:N&mode=search">Book</a></p></div></div>';

						entrySet = (entrySet+itemStorage);
					});

					$('.carousel-inner').append(entrySet);
				});

		$('.carousel').carousel({ interval: false });
	});
</script>
</body>
</html>