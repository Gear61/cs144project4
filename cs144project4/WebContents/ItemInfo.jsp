<html>
<head>
<title><%=request.getAttribute("title")%></title>
</head>
<body>
	<a href="./">Return to Homepage</a>
	<br>
	<FORM METHOD="GET" ENCTYPE="multipart/form-data" ACTION=item>
		Item ID: <INPUT TYPE="text" NAME="id"> <INPUT TYPE=SUBMIT
			VALUE="Do a New Item Search">
	</FORM>

	<h2>Item Information</h2>
	<br>
	<b>Item ID:</b>
	<%=request.getAttribute("itemID")%>
	<br>
	<b>Name:</b>
	<%=request.getAttribute("name")%>
	<br>
	<dl>
		<dt>
			<b>Category:</b>
		</dt>
		<%=request.getAttribute("category")%>
		<br>
		<b>Currently:</b>
		<%=request.getAttribute("currently")%>
		<br>
		<b>First Bid:</b>
		<%=request.getAttribute("first_bid")%>
		<br>
		<b>Number of Bids:</b>
		<%=request.getAttribute("num_bids")%>
		<br>
		<b>Bids:</b>
		<%=request.getAttribute("bid")%>
		<br>
		<b>Location:</b>
		<%=request.getAttribute("location")%>

		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		<style type="text/css">
		html {
			height: 100%
		}
		
		body {
			height: 100%;
			margin: 0px;
			padding: 0px
		}
		
		#map_canvas {
			height: 100%
		}
		</style>
		<script type="text/javascript"
					src="http://maps.google.com/maps/api/js?sensor=false">
		</script>
		<script type="text/javascript">
		  function initialize() {
		    var latlng = new google.maps.LatLng(34.063509,-118.44541);
		    var myOptions = {
		      zoom: 14, // default is 8 
		      center: latlng,
		      mapTypeId: google.maps.MapTypeId.ROADMAP
		    };
		    var map = new google.maps.Map(document.getElementById("map_canvas"),
		        myOptions);
		  }
		</script>
		<br>
		<b>Country:</b>
		<%=request.getAttribute("country")%>
		<br>
		<b>Started:</b>
		<%=request.getAttribute("started")%>
		<br>
		<b>Ends:</b>
		<%=request.getAttribute("ends")%>
		<br>
		<b>Seller ID:</b>
		<%=request.getAttribute("sellerID")%>
		<br>
		<b>Seller Rating:</b>
		<%=request.getAttribute("rating")%>
		<br>
		<b>Description:</b>
		<%=request.getAttribute("description")%>
		<br>
</body>
<body onload="initialize()">
	<div id="map_canvas" style="width: 100%; height: 100%"></div>
</body>
</html>