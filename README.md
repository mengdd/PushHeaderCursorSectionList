
PushHeaderCursorSectionList

The push header section ListView.
The ListView show several sections and each section has a header as title.
At the top of the ListView, a child view show the current first header.
When the next header is coming up, the top header shows to be pushed up.

The original Demo is shown at:
https://github.com/mengdd/PushHeaderSectionList

Here, I extends the Adapter from the CompositeCursorAdapter,
which is the google Android source code from: 
The whole source code can be download at: https://android.googlesource.com/
CompositeCursorAdapter is from the Sample of Contacts: 
https://android.googlesource.com/platform/packages/apps/Contacts/

In that way, the adapter is suitable for the Cursor data.
