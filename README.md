pUI
===

A Graphical User Interface Library for Processing

The goal of this project is to create a functional prototype for a new Graphical User Interface Library for Processing. There is no core GUI system built into Processing as of date but some third-party GUI solutions exist. This project attempts to learn from and build on the existing libraries while keeping in mind the design principles that lie at the heart of Processing: Minimalism, accessibility and extensibility. Ultimately the aim is to spark discussion on what a core GUI library for Processing might encompass.

Project Info: http://martinleopold.com/pui/ <br/>
Full Project Description: http://static.martinleopold.com/gsoc13/ <br/>
Source: https://github.com/martinleopold/pUI

CURRENT RELEASE: 
v0.2 (Test Release)

DOWNLOAD: 
https://github.com/martinleopold/pUI/raw/v0.2/dist/pUI-508.zip

INSTALLATION:
* Requires Processing 2.0 http://www.processing.org/download
* If not already present, create a folder named "libraries" inside your Sketchbook folder. (The location of the sketchbook folder is shown in Processing's Preferences dialog)
* Extract the .zip into the "libraries" folder. (There is one folder named "pUI" in the .zip)
* Restart Processing

EXAMPLES:
* In Processing click File -> Examples... then select Contributed Libraries/pUI and select an example

FEATURES:
* GUI Overlay
* Widgets: Button, Toggle, Slider, VSlider, Label, Divider
* Create new widget by extending the Widget class
* Automatic Column Based Layout
* Color Themes
* Callback Methods
* Connect Widgets to Fields
* Chainable Methods


API
===

PUI
* PUI.init(PApplet) : PUI // init the library with the sketch (e.g PUI.init(this))
* size(int width, int height) : PUI // set the size of the GUI overlay in px
* position(int x, int y) : PUI // set the position of the GUI overlay in px
* addButton() : Button // add a Button
* addToggle() : Toggle // add a Toggle
* addSlider() : Slider // add a Slider (horizontal)
* addVSlider() : VSlider // add a vertical Slider
* addDivider() : Divider // add a Divider
* addLabel(String text) : Label // add a Label
* newRow() : PUI // following widgets will be created in the next row
* newColumn() : PUI // following widgets will be created in another column
* grid(int x, int y) : PUI // set grid unit in pixels
* padding(float x, float y) : PUI // set padding (around all widgets) in grid units
* columnWidth(float w) : PUI // set column width (in grid units). widgets including padding are fit in this width
* columnGap(float g) : PUI // set gap between columns (in grid units)
* show() : PUI // show GUI overlay
* hide() : PUI // hide GUI overlay
* toggle() : PUI // toggle GUI overlay
* toggleGrid() : PUI // toggle drawing grid
* font(String font) : PUI // set font by name (uses createFont())
* font(PFont font) : PUI // set font
* theme(Theme t) : PUI // set color theme (e.g. Theme.DEFAULT, Theme.METALGEAR, Theme.MINBLACK, Theme.PEPTOBISMOL)

All Widgets
* position(float x, float y) : &lt;WidgetType&gt; // set widget position in grid units
* size(float width, float height)) : &lt;WidgetType&gt; // set widget size in grid units
* onMouse(String method) : &lt;WidgetType&gt; // method to be called when a mouse event happens. Parameters: MouseEvent
* onDraw(String method) : &lt;WidgetType&gt; // method to be called when the widget is drawn. Parameters: &lt;WidgetType&gt;

Button
* onClick(String method) : Button // method to be called when the button is clicked. Parameters: Button
* connect(String field) : Button // name of boolean field to connect the button to
* label(String text) : Button // set label text
* noLabel() : Button // remove the label
* clicked : boolean // true if the button is being clicked

Toggle
* onClick(String method) : Toggle // method to be called when the toggle is toggled. Parameters: Toggle
* connect(String field) : Toggle // name of a boolean field to connect the toggle to
* label(String text) : Toggle // set label text
* noLabel() : Toggle // remove the label
* on : boolean // true if the toggle is on

Slider, VSlider
* onValue(String method) : Slider // method to be called when the value is changed. Parameters: Slider
* connect(String field) : Slider // name of a float field to connect the slider to
* label(String text): Slider // set label text
* noLabel() : Slider // remove the label
* min(float min) : Slider // set minimum value
* max(float max) : Slider // set maximum value
* value : float // current value of the slider between 0 and 1

Label
* text(String text) : Label // set label text
* small() : Label // use small size
* medium() : Label // use medium size
* large() : Label // use large size
