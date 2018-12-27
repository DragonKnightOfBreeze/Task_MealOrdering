<!DOCTYPE html>
<html>
<head>
	<title>我的主页</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>

	<script>
		$(function() {
			$("#mo_header").load("/mealordering/mo_header.html");
			$("#mo_background-bar").load("/mealordering/mo_background-bar.html");
			$("#mo_footer").load("/mealordering/mo_footer.html");

		});
	</script>


</head>

<body>
	<div id="mo_header"></div>

	<div id="mo_background-bar"></div>

	<div class="container" id="mo_body">
		<!--STEP 侧边信息栏-->
		<div class="col-sm-4">


		</div>

		<!--STEP 我的主页-->
		<div class="col-sm-8">
			<div class="container" id="mo_body-content">

			</div>
		</div>
	</div>

	<div id="mo_footer"></div>
</body>
</html>
