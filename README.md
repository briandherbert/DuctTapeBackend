DuctTapeBackend
=========

WhatIHadForBreakfast is an Android app to demo DuctTapeBackend, which is a quick-and-dirty way to supply your Android (for now) app with data from afar. You make a Google spreadsheet, and your app pulls in the data as a two dimensional String array.  Like duct tape, it is:

  - Single sided. From the device's perspective, the data is read-only
  - Not the Right Way to solve the problem
  - Very quick and easy to use
  - A good way to get engineers to make fun of you

Here's an example. I make a Google spreadsheet like this:

![](/../photos/photos/fattyDoc.png?raw=true "Optional Title")

Which, in the WhatIHadForBreakfast app, looks like this:

![](/../photos/photos/fattyScreen.png?raw=true "Optional Title")

Then I change the cells in my spreadsheet to this:

![](/../photos/photos/spreadsheetCharms.png?raw=true "Optional Title")

And my app changes when it fetches the data again (restart or hit "refresh"):

![](/../photos/photos/charmsScreen.png?raw=true "Optional Title")


The simplest way to use it is to copy
[DuctTapeBackend.java][1]


[1]:https://github.com/briandherbert/WhatIHadForBreakfast/blob/master/src/com/example/whatihadforbreakfast/DuctTapeBackend.java into your Android project and follow the javadoc instructions. Or you can just use the WhatIHadForBreakfast app provided here.

Once your app has the 
