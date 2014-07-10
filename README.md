DuctTapeBackend
=========

WhatIHadForBreakfast is an Android app to demo DuctTapeBackend, which is a quick-and-dirty way to supply your Android (for now) app with data from afar. You make a Google spreadsheet, and your app pulls in the data as a two dimensional String array.  Like duct tape, it is:

  - Single sided. From the device's perspective, the data is read-only
  - Not the Right Way to solve the problem
  - Very quick and easy to use
  - A good way to get engineers to make fun of you


Demo
-----------

On the left is the Google Docs spreadsheet, on the right is an Android emulator running the WhatIHadForBreakfast app:

![](/../photos/photos/breakfastQuick.gif?raw=true "Optional Title")

If that gif isn't working, here are screenshots of the doc and emulator [before][2] and [after][3]. The app uses DuctTapeBackend to pull in the text data from the Google Doc.

To try it out yourself, copy [DuctTapeBackend.java][1] into your Android project and follow the javadoc instructions. Or you can just use the WhatIHadForBreakfast app provided here. The short version is:

1. Make a Google Doc, and set the Sharing permission to "Anyone with a link can view".
2. Copy the doc key from the URL. It's that long string of letters, numbers, and dashes.
3. Call DuctTape


[1]:https://github.com/briandherbert/WhatIHadForBreakfast/blob/master/src/com/example/whatihadforbreakfast/DuctTapeBackend.java 
[2]:https://raw.githubusercontent.com/briandherbert/WhatIHadForBreakfast/photos/photos/fattySides.png
[3]:https://raw.githubusercontent.com/briandherbert/WhatIHadForBreakfast/photos/photos/charmsSides.png
