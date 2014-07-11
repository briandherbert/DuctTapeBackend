DuctTapeBackend
=========

WhatIHadForBreakfast is an Android app to demo DuctTapeBackend, which is a quick-and-dirty way to supply your Android (for now) app with data from afar. You make a Google spreadsheet, and your app pulls down all the values from it. Like duct tape, it is:

  - Single-sided. From the device's perspective, the data is read-only
  - Not the Right Way to solve the problem
  - Good for prototyping and solving problems that aren't mission-critical
  - Very quick and easy to use
  - A good way to get engineers to mock you


Demo
-----------

On the left is the Google Docs spreadsheet, on the right is an Android emulator running the WhatIHadForBreakfast app. The app uses DuctTapeBackend to fetch data from the spreadsheet and then updates the UI:

![](/../photos/photos/breakfastQuick.gif?raw=true "Optional Title")

The loop makes it a little hard to folllow, but it starts with the "Fatty Special" and changes to "Lucky Charms". If that gif isn't working, here are screenshots of the doc and emulator [before][2] and [after][3].

To try it out yourself, copy [DuctTapeBackend.java][1] into your Android project and follow the javadoc instructions. Or you can just use the WhatIHadForBreakfast app provided here. The short version is:

1. Make a Google Doc, and set the Sharing permission to "Anyone with a link can view".
2. Copy the doc key from the URL. It's that long string of letters, numbers, and dashes.
3. Call DuctTapeBackend.downloadGoogleSpreadsheetData(), passing in the doc key and a listener (which is the Activity itself in the sample app) for when the data is done.
4. Your listener will get the spreadsheet data back as a CSV (comma separated values) String. If you want to convert the data to a two-dimensional String for of row, column cell data, call DuctTapeBackend.parseCsvToRowColData()
5. Remember to do any related UI updates on the UI thread (Search "runOnUiThread" in the sample app or on Google)


[1]:https://github.com/briandherbert/WhatIHadForBreakfast/blob/master/src/com/example/whatihadforbreakfast/DuctTapeBackend.java 
[2]:https://raw.githubusercontent.com/briandherbert/WhatIHadForBreakfast/photos/photos/fattySides.png
[3]:https://raw.githubusercontent.com/briandherbert/WhatIHadForBreakfast/photos/photos/charmsSides.png
