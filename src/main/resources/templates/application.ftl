<!DOCTYPE html>
<html>
<head>
	<title>Fly Away</title>
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<style>
		.carousel-inner > .item > a > img, .carousel-inner > .item > img {
			line-height: 1;
			margin: 0 auto;
		}
		.item{
			min-height: 290px;
			max-height: 300px;
		}
		.glyphicon-remove{
			color: #CC5E5C;
		}
		.carousel-control .glyphicon-remove,
		.carousel-control .glyphicon-heart,
		.carousel-control .icon-next,
		.carousel-control .icon-prev{
			color: #83a976;;
			display: inline-block;
		margin-top: -10px;
			opacity: 1;
		position: absolute;
		top: 130%;
		z-index: 5;
		}
	</style>
</head>
<body>

	<div id="server-side-content">
		<img src="/static/loading.gif">
	</div>
	
	<div id="client-side-content">
		
	</div>
	
	<#include "handlebars-templates.ftl" >
	
	<script src="/static/scripts/common/jquery-1.11.3.min.js"></script>
	<script src="/static/scripts/common/ember.min.js"></script>
	<script src="/static/scripts/common/ember-template-compiler.js"></script>
	<script src="/static/scripts/app/application.js"></script>
	<script src="/static/scripts/app/rate.js"></script>
	
	<script src="/static/js/bootstrap.min.js"></script>
	<script src="/static/js/carousel-swipe.js"></script>
</body>
</html>