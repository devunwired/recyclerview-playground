Android RecyclerView Examples
-----------------------------

This repository contains examples for using the RecyclerView widget found in the Android Support Library.

Disclaimer
----------
This repository contains sample code intended to demonstrate the capabilities of the RecyclerView layout manager APIs. It is not intended to be used as-is in applications as a library dependency, and will not be maintained as such. Bug fix contributions are welcome, but issues and feature requests will not be addressed.

Example Contents
----------------
The following bits can be found in the main sample application:
- Implementation of `LinearLayoutManager` and `GridLayoutManager` for vertical and horizontal scrolling.
- Custom ItemDecorations
 - `InsetDecoration` - Create an inset margin on all child views.
 - `DividerDecoration` - Create an inset margin and draw dividers below vertical child views.
 - `GridDividerDecoration` - Create an inset margin an draw dividers along grid lines
- Custom LayoutManager
 - `FixedGridLayoutManager` - Similar to `StaticGridLayoutManager`, but with a controllable column count.
 
The following examples are incubating on the `experimental` branch (these mostly work, if you feel like living dangerously):
- Custom LayoutManagers
 - `StaticGridLayoutManager` - 2D scrolling grid with variable column count based on data set. Window of visible (non-recycled) views is determined statically.
 - `DynamicGridLayoutManager` - 2D scrolling grid where window of visible views is determined dynamically. Results in fewer views in memory, but scrolling performance is questionable.

License
-------

The code supplied here is covered under the MIT Open Source License:

Copyright (c) 2015 Wireless Designs, LLC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.