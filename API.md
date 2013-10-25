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
* getNumThemes() : int // get the number of preset color themes (for use with theme())
* getThemeNames() : String[] // get the names of the preset color themes (for use with theme());


All Widgets
* position(float x, float y) : &lt;WidgetType&gt; // set widget position in grid units
* size(float width, float height)) : &lt;WidgetType&gt; // set widget size in grid units
* onMouse(String method) : &lt;WidgetType&gt; // method to be called when a mouse event happens. Parameters: MouseEvent
* onDraw(String method) : &lt;WidgetType&gt; // method to be called when the widget is drawn. Parameters: &lt;WidgetType&gt;
* isActive() : boolean
* isVisible() : boolean
* isHovered() : boolean
* isPressed() : boolean
* isDragged() : boolean
* activate() :
* deactivate() :
* show() :
* hide() :
* positionX() : 
* positionY() : 
* width() : 
* height() :
* mouseX() :
* mouseY() :


Button
* onClick(String) : Button // method to be called when the button is clicked. Parameters: Button
* connect(String) : Button // name of boolean field to connect the button to
* label(String) : Button // set label text
* noLabel() : Button // remove the label
* isPressed(boolean) : Button 

* isPressed() : boolean // true if the button is being pressed down


Toggle
* onClick(String) : Toggle // method to be called when the toggle is toggled. Parameters: Toggle
* connect(String) : Toggle // name of a boolean field to connect the toggle to
* label(String) : Toggle // set label text
* noLabel() : Toggle // remove the label
* isPressed(boolean) : Toggle

* isPressed() : boolean // true if the toggle is on


Slider, VSlider
* onValue(String) : Slider // method to be called when the value is changed. Parameters: Slider
* connect(String) : Slider // name of a float field to connect the slider to
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
