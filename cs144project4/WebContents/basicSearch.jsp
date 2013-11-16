<html>
<head>
    <title>Basic Search Results</title>
</head>
<body>
	<a href="./">Return to Homepage</a>
	<br><FORM METHOD="GET" ENCTYPE="multipart/form-data" ACTION=search>
	Keyword(s): <INPUT TYPE="text" NAME="q">
	<INPUT TYPE="hidden" NAME="numResultsToSkip" value="0">
	<INPUT TYPE="hidden" NAME="numResultsToReturn" value="20">
	<INPUT TYPE=SUBMIT VALUE="New Keyword Search">
	</FORM>

	<h1>Here are the results for your query: <%= request.getAttribute("query") %></h1>
    <%= request.getAttribute("results") %>
</body>
</html>