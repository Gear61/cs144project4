<html>
<head>
<script type="text/javascript">

var xmlHttp = new XMLHttpRequest(); // works only for Firefox, Safari, ...

// send Google suggest request based on the user input
function sendAjaxRequest(input)
{
  var request = "/eBay/suggest?q="+encodeURI(input);

  xmlHttp.open("GET", request); 
  xmlHttp.onreadystatechange = showSuggestion; 
  xmlHttp.send(null);
}

// update Web page with the response from Google suggest
function showSuggestion() {
  if (xmlHttp.readyState == 4) {
    // get the CompleteSuggestion elements from the response
    var s = xmlHttp.responseXML.getElementsByTagName('CompleteSuggestion');
 
    // construct a bullet list from the suggestions
    var htmlCode = "<ul>";
    for (i = 0; i < s.length; i++) {
       var text = s[i].childNodes[0].getAttribute("data");

       htmlCode += "<li><b>" + text + "</b></li>";
    }
    htmlCode += "</ul>";

    // display the list on the page
    document.getElementById("suggestion").innerHTML = htmlCode;
  }
}
</script>
</head>
<body>
  <b>Your query:</b> <input type="text" onKeyUp="sendAjaxRequest(this.value);" /><br/>
  <b>Suggestion</b>: <pre id="suggestion"></pre>
</body>
</html>