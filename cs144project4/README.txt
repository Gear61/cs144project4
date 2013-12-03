Q1: For which communication(s) do you use the SSL encryption? If you are encrypting the communication from (1) to (2) in Figure 2, for example, write (1)→(2) in your answer.

We use SSL encryption for (4)->(5) and (5)->(6), since only those communications include the user’s credit card information.

Q2: How do you ensure that the item was purchased exactly at the Buy_Price of that particular item?

We associate all item information with the HTTP session upon the loading of an item’s information page, so it is encapsulated from the user.

Q3: How do you guarantee that the user cannot scroll horizontally?

We set the scale to a constant and then disabled user-scalable variable to 'no' in our meta statement.

Q4: How do you guarantee that the width of your textbox component(s) can fit the screen width of a mobile device? Note: you have to explain "how", and you can't simply state that "we use a XXX downloaded from YYY, and it magically solve the problem."

Along with Q3, dyanmically checked and set the width of the window to the device's width in our meta statement.

This is the meta tag used in reference to Q3 and Q4.
<meta name="viewport" charset="utf-8" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>

Partner:
Name: Won Kyu Lee
SID: 904083134
Email: wlee89@ucla.edu
