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
		.container-fluid {
			padding-left: 0;
			padding-right: 0;
		}
		.row {
			margin-right: 0;
			margin-left: 0;
		}
		.col-sm-12 {
			padding-left: 0;
			padding-right: 0;
		}
        img .background {
			height: 100%;
			width: auto;
		}
		.glyphicon {
			top: 85%;
		}
		.glyphicon-remove {
			left: 25%;
		}
        .glyphicon-heart {
            right: 25%;
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
		.explore a, .explore a:hover{
			color: #ffffff;
			text-decoration: none;
		}
		.book-and-explore {
            position: absolute;
            top: 85%;
            left: 45%;
		}
        .btn-success {
            position: absolute;
            top: 5%;
            right: 15%;
            z-index: 999;
        }
		.explore-inner {
			position: relative;
			left: -40%;
            display: block;
            text-align: center;
		}
        .book-inner {
            left: -50%;
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