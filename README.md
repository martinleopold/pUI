pUI
===

Graphical User Interface Library for Processing

Project Info:
http://static.martinleopold.com/gsoc13/

Current Release: 
v0.1<br />
Test Release<br />

Download: https://github.com/martinleopold/pUI/raw/495d333ad5ea19851441340d8602a6b0771a874a/dist/pUI-320.zip

INSTALLATION:
* Requires Processing 2.0 http://www.processing.org/download
* If not already present, create a folder named "libraries" inside your Sketchbook folder. (The location of the sketchbook folder is shown in Processing's Preferences dialog)
* Extract the .zip into the "libraries" folder. (There is one folder named "pUI" in the .zip)
* Restart Processing.

EXAMPLES:
* In Processing click File -> Examples... then select Contributed Libraries/pUI and select an example

FEATURES:
* GUI Overlay
* Widgets: Button, Toggle, Slider, Label, Divider
* Automatic Column Based Layout
* Color Themes
* Callback Methods
* Connect Widgets to Fields
* Chainable Methods


API
===

PUI
* PUI.init(PApplet)
* addButton()
* addToggle()
* addSlider()
* addDivider()
* addLabel(String text)
* newRow()
* newColumn()
* grid(int x, int y)
* padding(float x, float y)
* show()
* hide()
* toggle()
* toggleGrid()
* font(String font)
* theme(Theme t)

Button
* onClick(String method)
* connect(String field)
* label(String text)
* noLabel()

Toggle
* onClick(String method)
* connect(String field)
* label(String text)
* noLabel()

Slider
* onValue(String method)
* connect(String field)
* label(String text)
* noLabel()

Label
* text(String text)

All Widgets
* onMouse(String method)
* onDraw(String method)
