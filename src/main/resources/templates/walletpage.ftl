<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Fly Away: Wallet</title>
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<style>
	    .container-fluid {
	        padding-left: 0;
	        padding-right: 0;
	    }
	    .media {
	        margin-top: 0;
	        border-bottom: 1px solid #efefef;
	    }
	    .media:last-child {
	        border-bottom: 0;
	    }
	    .media-body, .media-left, .media-right {
            vertical-align: middle;
        }
        .media-right {
            padding-right: 10px;
        }
        .btn-default {
            background-color: #fff;
            border-color: #5cb85c;
            color: #5cb85c;
            font-size: 18px;
            font-weight: bold;
        }
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12">
			<!--start-->

        <#list suggestions as suggestion>
            <div class="media">
                <div class="media-left">
                    <a href="#">
                        <img class="media-object" style="max-height:150px;max-width:150px;" src="static/assets/${suggestion.getDestinationAirport().getCode()}.jpg" alt="${suggestion.getDestinationAirport().getCode()}">
                    </a>
                </div>
                <div class="media-body">
                    <h4 class="media-heading">${suggestion.getDestinationAirport().getCity()}</h4>
                </div>
                <div class="media-right">
                    <button type="button" class="btn btn-default" onclick="location.href='${suggestion.getDeeplinkURL()}'">${suggestion.getPrice()}</button>
                </div>
            </div>
        </#list>

			<!--end-->
		</div>
	</div>
</div>

<script src="/static/scripts/common/jquery-1.11.3.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>

</body>
</html>