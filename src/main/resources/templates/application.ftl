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
        html, body {
            width: 100%;
            height: 100%;
        }
        .ember-application, .ember-view, .container-fluid, .row, .col-sm-12, .carousel, .carousel-inner, .item {
			width: inherit;
			height: inherit;
		}
        img .background {
			height: 100%;
			width: auto;
		}
		.glyphicon {
			top: 85%;
		}
		.glyphicon-remove {
			left: 20%;
		}
        .glyphicon-heart {
            right: 20%;
        }
		.carousel-control .glyphicon-remove,
		.carousel-control .glyphicon-heart,
		.carousel-control .icon-next,
		.carousel-control .icon-prev{
			color: #ffffff;;
			display: inline-block;
			opacity: 1;
			position: absolute;
			z-index: 5;
            font-size: 36px;
		}
		.icon-next {
			right: 10%;
		}
		.icon-prev {
			left: 10%;
		}
		.carousel-caption {
			top: 10%;
			left: 10%;
			text-align: left;
		}
		.btn-warning {
			position: absolute;
			top: 85%;
			left: 45%;
		}

        .btn-success {
            position: absolute;
            top: 5%;
            left: 85%;
        }
		h2 {
			font-size: 3em;
            margin-bottom: 0;
		}
		h4 {
			font-size: 2em;
			margin-top: 0;
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