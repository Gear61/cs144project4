<html>
<head>
<title><%=request.getAttribute("title")%></title>
</head>
<body onload="setBids()">
	<a href="./">Return to Homepage</a>
	<br><br>
	<FORM METHOD="GET" ENCTYPE="multipart/form-data" ACTION=item>
		Item ID: <INPUT TYPE="text" NAME="id"> <INPUT TYPE=SUBMIT
			VALUE="Do a New Item Search">
	</FORM>

	<h2>Item Information</h2>
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
		
		<b>Currently:</b>
		<%=request.getAttribute("currently")%>
		<br>
		
		<b>First Bid:</b>
		<%=request.getAttribute("first_bid")%>
		<br>
		
		<b>Number of Bids:</b>
		<%=request.getAttribute("num_bids")%>
		<br>
		
		<div id="Bids"></div>
		<input id="bidList" type="hidden" value="<%=request.getAttribute("bid")%>">
		
		<b>Location:</b>
		<%=request.getAttribute("location")%>
		
		<br><br>
		<div id="locationMessage"></div>
		<input id="address" type="hidden" value="<%=request.getAttribute("location")%>">

		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCP0_piFAKeDJTulBg18778H9W5X4qk2a4&sensor=false"></script>
		<script>
		function setBids()
		{
		  var bids = document.getElementById('bidList').value;
		  var prefix = "<b>Bids: </b>"
		  if (bids == "<dl></dl>")
  		  {
  			document.getElementById('Bids').innerHTML = prefix.concat("There are currently no bids on this auction.<br><br>");
  		  }
		  else
  		  {
  			document.getElementById('Bids').innerHTML = prefix.concat(bids);
  		  }
		}
		
		var geocoder;
		var map;
		function initialize()
		{
		  var address = document.getElementById('address').value;
		  geocoder = new google.maps.Geocoder();
		  geocoder.geocode( { 'address': address}, function(results, status)
		  {
		    if (status == google.maps.GeocoderStatus.OK)
		    {
		      console.log('Geocode succeeded!');
		      var latlng = (results[0].geometry.location);
		      var mapOptions =
		      {
		    	zoom: 15,
		    	center: latlng
		  	  }
		      map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
		      var marker = new google.maps.Marker({
		          map: map,
		          position: results[0].geometry.location
		      });
		      document.getElementById('locationMessage').innerHTML = "Google maps successfully found the location of the item. Map below.";
		    }
		    else
		    {
		      var latlng = new google.maps.LatLng(0, 0);
		      document.getElementById('locationMessage').innerHTML = "Google maps was unable to find the location of the item.";
		      var mapOptions =
		      {
		    	zoom: 1,
		    	center: latlng
		  	  }
		      map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
		      console.log('Geocode was not successful for the following reason: ' + status);
		    }
		  });
		}

		google.maps.event.addDomListener(window, 'load', initialize);
   		</script>
   		
   		<body onload="initialize()">
			<div id="map_canvas" style="width: 50%; height: 100%"></div>
		</body>
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
</html>