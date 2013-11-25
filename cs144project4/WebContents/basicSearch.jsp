<html>
<head>
    <title>Basic Search Results</title>
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