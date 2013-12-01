<html>
<head>
    <title>Purchase Confirmation</title>
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

#innerContainer {
    display: inline-block;
    text-align: left;
}
</style>
<body>
	<div id="outerContainer">
		<a href="./unsecure">Return to Homepage</a><br><br>
		Purchase successful!<br><br>
    	<div id="innerContainer">
			<b>Item ID: </b><%= session.getAttribute("itemID") %><br>
			<b>Item Name: </b><%= session.getAttribute("itemName") %><br>
			<b>Buy Price: </b><%= session.getAttribute("buyPrice") %><br>
			<b>Credit Card Number: </b><%= session.getAttribute("creditCardNumber") %><br>
			<b>Time of Purchase: </b><%= session.getAttribute("timeOfPurchase") %>
    	</div>
	</div>
</body>
</html>