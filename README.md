Android RecyclerView Examples
-----------------------------

This repository contains examples for using the RecyclerView widget found in the Android Support Library.

Pre-Android "L" Note
--------------------
While Android "L" is in preview, the support library associated with RecyclerView has its minimum SDK version set to L (v21). At least for the time being, in order to use these samples on an earlier Android device (they work back to v7), add the following line to your AndroidManifest.xml file (under the `<manifest>` element:

    <uses-sdk tools:node="replace" />

This will cause Gradle to ignore the updated SDK version in the library.

Example Contents
----------------
The following bits can be found in the main sample application:
- Implementation of `LinearLayoutManager` for vertical and horizontal scrolling.
- Custom ItemDecorations
 - `InsetDecoration` - Create an inset margin on all child views.
 - `DividerDecoration` - Create an inset margin and draw dividers below vertical child views.
- Custom LayoutManagers
 - `StaticGridLayoutManager` - 2D scrolling grid with variable column count based on data set. Window of visible (non-recycled) views is determined statically.
 - `FixedGridLayoutManager` - Similar to `StaticGridLayoutManager`, but with a controllable column count.
 
The following examples are incubating on the `experimental` branch (these mostly work, if you feel like living dangerously):
- Custom LayoutManagers
 - `DynamicGridLayoutManager` - 2D scrolling grid where window of visible views is determined dynamically. Results in fewer views in memory, but scrolling performance is questionable.

License
-------

The code supplied here is covered under the MIT Open Source License:

Copyright (c) 2014 Wireless Designs, LLC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.