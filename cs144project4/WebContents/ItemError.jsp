<html>
<head>
	<title>Error for Item ID: "<%= request.getAttribute("id") %>"</title>
</head>
<body>
	<a href="./">Return to Homepage</a>
	<br><FORM METHOD="GET" ENCTYPE="multipart/form-data" ACTION=item>
		Item ID: <INPUT TYPE="text" NAME="id"> <INPUT TYPE=SUBMIT
			VALUE="Do a New Item Search">
	</FORM>
	<h1>Item Information for "<%= request.getAttribute("id") %>" could not be found</h1> </br>
</body>
</html>