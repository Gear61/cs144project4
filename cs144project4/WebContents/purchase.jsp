<html>
<head>
    <title>Item Purchase Page</title>
	<script>
	
	function itemID()
	{
	  // This function is anonymous, is executed immediately and 
	  // the return value is assigned to QueryString!
	  var query = window.location.search.substring(1);
	  var vars = query.split("id=");
	  var itemID = vars[1]
	  return itemID;
	};
	
	function loadPage()
	{
		var getString = document.getElementById('itemList').value;
		var byID = getString.split(itemID()+":");
		var byPie = byID[1].split("~~|}");
		var name = byPie[0];
		var byRPie = byPie[1].split("{|~~");
		var price = byRPie[0];
		document.getElementById('itemID').innerHTML="<b>Item ID: </b>" + itemID();
		document.getElementById('itemName').innerHTML="<b>Name: </b> " + name;
		document.getElementById('buyPrice').innerHTML="<b>Buy Price: </b> " + price;
		
		document.getElementById('purchaseID').value=itemID();
	}
	
	</script>
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

<body onload="loadPage()">
	<div id="outerContainer">
		<a href="./unsecure">Return to Homepage</a><br><br>
		Simply enter in your credit card number and
		<br>click the "Purchase Now" button to continue.<br><br>
    	<div id="innerContainer">
			<div id="itemID"></div>
			<div id="itemName"></div>
			<div id="buyPrice"></div>
			<FORM METHOD="GET" ACTION=confirm>
			<b>Credit Card Number: </b><INPUT TYPE="text" NAME="creditCardNumber">
			<INPUT TYPE="hidden" NAME="purchaseID" id="purchaseID" value="">
    	</div>
    	<br><INPUT TYPE=SUBMIT VALUE="Purchase Now">
		</FORM>
	</div>
	<FORM>
			<INPUT TYPE="hidden" NAME="itemList" ID="itemList" value="<%= session.getAttribute("itemList") %>">
	</FORM>
</body>
</html>