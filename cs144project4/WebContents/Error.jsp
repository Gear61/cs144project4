<html>
<head>
    <title>Error</title>
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

#outerContainer {
    width: 100%;
    text-align: center;
}

</style>
<body>
	<div id="outerContainer">
		<a href="./">Return to Homepage</a>
		<h1>Woops!</h1>
		<%= request.getAttribute("errorMessage") %>
	</div>
</body>
</html>