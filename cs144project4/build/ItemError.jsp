<html>
<head>
	<title>Error for Item ID: "<%= request.getAttribute("id") %>"</title>
</head>
	<style> 
body
{
	background-size:100%;
	background-repeat:repeat-y;
	background-color:#E6E6E6;
	background-position:center;
}
div.content
{
	position: absolute;
	top: 35%;
	left: 50%;
	margin-left: -250px;
	margin-top: -52.5px
}
</style>
<body>
	<a href="./">Return to Homepage</a>
	<br><FORM METHOD="GET" ENCTYPE="multipart/form-data" ACTION=item>
		Item ID: <INPUT TYPE="text" NAME="id"> <INPUT TYPE=SUBMIT
			VALUE="Do a New Item Search">
	</FORM>
	<h1>Item Information for "<%= request.getAttribute("id") %>" could not be found</h1> </br>
</body>
</html>