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

If that gif isn't working, here are screenshots of the doc and emulator [before][2] and [after][3]


To try it out yourself, copy [DuctTapeBackend.java][1] into your Android project and follow the javadoc instructions. Or you can just use the WhatIHadForBreakfast app provided here.


[1]:https://github.com/briandherbert/WhatIHadForBreakfast/blob/master/src/com/example/whatihadforbreakfast/DuctTapeBackend.java 
[2]:https://github.com/briandherbert/WhatIHadForBreakfast/blob/photos/photos/charmsSides.png
[3]:https://github.com/briandherbert/WhatIHadForBreakfast/blob/photos/photos/fattySides.png
