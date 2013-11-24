/**
 * Provides suggestions for state names (USA).
 * 
 * @class
 * @scope public
 */

var xmlHttp = new XMLHttpRequest();
var control;
var typeAhead;

function StateSuggestions()
{
}

// update Web page with the response from Google suggest
function showSuggestion()
{
	if (xmlHttp.readyState == 4)
	{
		var aSuggestions = [];

		// get the CompleteSuggestion elements from the response

		if (xmlHttp.responseXML == null)
		{
			control.hideSuggestions();
		}
		else
		{
			var s = xmlHttp.responseXML
					.getElementsByTagName('CompleteSuggestion');

			for (i = 0; i < s.length; i++)
			{
				var text = s[i].childNodes[0].getAttribute("data");
				aSuggestions.push(text);
			}
		}

		// provide suggestions to the control
		control.autosuggest(aSuggestions, typeAhead);
	}
}

/**
 * Request suggestions for the given autosuggest control.
 * 
 * @scope protected
 * @param oAutoSuggestControl
 *            The autosuggest control to provide suggestions for.
 */

StateSuggestions.prototype.requestSuggestions = function(
		oAutoSuggestControl /* :AutoSuggestControl */, bTypeAhead /* :boolean */)
{
	// var aSuggestions = [];
	
	// Get what is currently in text box
	var sTextboxValue = oAutoSuggestControl.textbox.value;
	
	if (sTextboxValue.length > 0)
	{
		// send Google suggest request based on the user input
		var request = "/eBay/suggest?q="+encodeURI(sTextboxValue);
	
		xmlHttp.open("GET", request); 
		xmlHttp.onreadystatechange = showSuggestion; 
		xmlHttp.send(null);
		
		control = oAutoSuggestControl;
		typeAhead = bTypeAhead;
	}
};