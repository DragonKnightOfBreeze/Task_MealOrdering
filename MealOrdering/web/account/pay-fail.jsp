<!DOCTYPE html>
<html>
<head>
  <title>支付失败</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>

  <script>
		import * as utils from "/mealordering/assets/js/utils";

		$(function() {
			$("#mo_header").load("/mealordering/mo_header.html");
			$("#mo_background-bar").load("/mealordering/mo_background-bar.html");
			$("#mo_footer").load("/mealordering/mo_footer.html");

			utils.countDown($("#mo_second"), "index.html");
		});
  </script>

</head>

<body>
	<div id="mo_header"></div>

	<div id="mo_background-bar"></div>

	<div class="container" id="mo_body">
    <div class="row text-center m-3 text-warning">
      <h4>支付失败！</h4>
    </div>
    <hr>
    <div class="row text-center m-3 text-warning">
      <a class="btn btn-link" href="/mealordering/index.html">
				<span id="mo_second">5</span>秒后自动为您跳转到首页
      </a>
    </div>
  </div>

	<div id="mo_footer"></div>
</body>
</html>