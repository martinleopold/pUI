pUI
===

A Graphical User Interface Library for Processing

The goal of the project outlined in this proposal is to create a functional prototype for a new Graphical User Interface Library for Processing. There is no core GUI system built into Processing as of date but some third-party GUI solutions exist. This project attempts to learn from and build on the existing libraries while keeping in mind the design principles that lie at the heart of Processing: Minimalism, accessibility and extensibility. Ultimately the aim is to spark discussion on what a core GUI library for Processing might encompass.

Full Project Description: http://static.martinleopold.com/gsoc13/

CURRENT RELEASE: 
v0.1 (Test Release)

DOWNLOAD: 
https://github.com/martinleopold/pUI/raw/v0.1/dist/pUI-320.zip

INSTALLATION:
* Requires Processing 2.0 http://www.processing.org/download
* If not already present, create a folder named "libraries" inside your Sketchbook folder. (The location of the sketchbook folder is shown in Processing's Preferences dialog)
* Extract the .zip into the "libraries" folder. (There is one folder named "pUI" in the .zip)
* Restart Processing

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
* PUI.init(PApplet) : PUI
* addButton() : Button
* addToggle() : Toggle
* addSlider() : Slider
* addDivider() : Divider
* addLabel(String text) : Label
* newRow() : PUI
* newColumn() : PUI
* grid(int x, int y) : PUI
* padding(float x, float y) :PUI
* show() : PUI
* hide() : PUI
* toggle() : PUI
* toggleGrid() : PUI
* font(String font) : PUI
* theme(Theme t) : PUI

Button
* onClick(String method) : Button
* connect(String field) : Button
* label(String text) : Button
* noLabel() : Button

Toggle
* onClick(String method) : Toggle
* connect(String field) : Toggle
* label(String text) : Toggle
* noLabel() : Toggle

Slider
* onValue(String method) : Slider
* connect(String field) : Slider
* label(String text): Slider 
* noLabel() : Slider

Label
* text(String text) : Label

All Widgets
* onMouse(String method) : <WidgetType>
* onDraw(String method) : <WidgetType>
