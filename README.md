pUI
===

A Graphical User Interface Library for Processing

The goal of this project is to create a functional prototype for a new Graphical User Interface Library for Processing. There is no core GUI system built into Processing as of date but some third-party GUI solutions exist. This project attempts to learn from and build on the existing libraries while keeping in mind the design principles that lie at the heart of Processing: Minimalism, accessibility and extensibility. Ultimately the aim is to spark discussion on what a core GUI library for Processing might encompass.

Project Info: http://martinleopold.com/pui/ <br/>
Full Project Description: http://static.martinleopold.com/gsoc13/ <br/>
Source: https://github.com/martinleopold/pUI

CURRENT RELEASE: 
v0.3 (Test Release)
Note: This is still alpha, so expect changes in the API.

DOWNLOAD: 
https://github.com/martinleopold/pUI/raw/v0.3/dist/pUI-643.zip

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
* Connect Widgets to Methods and Fields
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
* theme(int presetNo) : PUI // set color theme by number (numbers 0 to getNumThemes()-1)
* theme(String themeName) : PUI // set color theme by name (array of names: getThemeNames())
* numThemes() : int // get the number of preset color themes (for use with theme())
* themeNames() : String[] // get the names of the preset color themes (for use with theme());


All Widgets
* position(float x, float y) : &lt;WidgetType&gt; // set widget position in grid units
* size(float width, float height)) : &lt;WidgetType&gt; // set widget size in grid units
* onMouse(String method) : &lt;WidgetType&gt; // method to be called when a mouse event happens. Parameters: MouseEvent
* onDraw(String method) : &lt;WidgetType&gt; // method to be called when the widget is drawn. Parameters: &lt;WidgetType&gt;
* isActive() : boolean // true if the widget receives mouse and keyboard events
* isVisible() : boolean // true if the widget is drawn
* isHovered() : boolean // true if the mouse is over the widget
* isClicked() : boolean // true if the widget is being clicked
* isDragged() : boolean // true if the mouse is clicked and moved over the widget
* activate() : &lt;WidgetType&gt; // enable receiving mouse and keyboard events
* deactivate() : &lt;WidgetType&gt; // disable receiving mouse and keyboard events
* show() : &lt;WidgetType&gt; // show the widget
* hide() : &lt;WidgetType&gt; // hide the widget
* positionX() : int // x coordinate of upper left hand corner
* positionY() : int // y coordinate of upper left hand corner
* width() : int // width of the widget in px
* height() : int // height of the widget in px
* mouseX() : int // x coordinate of last mouse position relative to upper left hand corner of the widget
* mouseY() : int // y coordinate of last mouse position relative to upper left hand corner of the widget


Button
* connect(String) : Button // connect the Button to all applicable methods and fields with the given name
* calls(String) : Button // name of a method to be called when the button is clicked. Parameters: () or Button
* sets(String) : Button // name of a field to set when the button value changes. Type: boolean
* label(String) : Button // set label text
* noLabel() : Button // remove the label
* isPressed(boolean) : Button
* isPressed() : boolean // true if the button is being pressed down


Toggle
* connect(String) : Toggle // connect the Toggle to all applicable methods and fields with the given name
* calls(String) : Toggle // method to be called when the toggle clicked. Parameters: boolean or Toggle
* sets(String) : Toggle // name of a field to set when the toggle value changes. Type: boolean
* label(String) : Toggle // set label text
* noLabel() : Toggle // remove the label
* isPressed(boolean) : Toggle
* isPressed() : boolean // true if the toggle is on


Slider, VSlider
* connect(String) : Slider // connect the Slider to all applicable methods and fields with the given name
* calls(String) : Slider // method to be called when the value is changed. Parameters: float or Slider
* sets(String) : Slider // name of a field to set when the slider value changes. Type: float
* label(String): Slider // set label text
* noLabel() : Slider // remove the label
* min(float ) : Slider // set minimum value
* max(float max) : Slider // set maximum value
* value() : float // current value of the slider between 0 and 1


Label
* text(String) : Label // set label text
* small() : Label // use small size
* medium() : Label // use medium size
* large() : Label // use large size
* text() : String
