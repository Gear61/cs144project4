<html>
<head>
<title><%=request.getAttribute("title")%></title>
</head>
<body>
	<a href="./">Return to Homepage</a>
	<br><FORM METHOD="GET" ENCTYPE="multipart/form-data" ACTION=item>
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
		<%= request.getAttribute("description") %>
		<br>
</body>
</html>