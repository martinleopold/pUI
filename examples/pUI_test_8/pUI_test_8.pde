import com.martinleopold.pui.*;

/*
  testing themes and widgets
*/
PUI ui;

boolean b2Value;
boolean t2Value;
float s2Value;

Slider s;

void setup() {
  size(500, 500);
  noSmooth();
  
  ui = PUI.init(this).size(300, height);
//  ui.position(10,10);
//  ui.grid(12,12); // set grid size
//  ui.padding(0.25, 0.25); // set padding (in grid units)
  ui.font("NewMedia Fett.ttf"); // set font
  
  ui.addLabel("Buttons");
  ui.newRow();
  ui.addButton().label("b1").calls("buttonClick");
  ui.addButton().label("b2").sets("b2Value");
  ui.addButton().label("b3").onMouse("mouseEvent");
  ui.addButton().label("b4").onDraw("drawEvent");
  ui.addDivider();
  
  ui.addLabel("Toggles");
  ui.newRow();
  ui.addToggle().label("t1").calls("toggleToggled");
  ui.addToggle().label("t2").sets("t2Value");
  ui.addToggle().label("t3");
  ui.addToggle().label("t4");
  ui.addDivider();
  
  ui.addLabel("Sliders");
  s = ui.addSlider().label("s1").calls("sliderValue").max(100);
  ui.addSlider().label("s2").sets("s2Value");
  ui.addDivider();
  
  ui.addLabel("VSliders"); ui.newRow();
  ui.addVSlider().label("v1").calls("vSliderValue");
  ui.addVSlider().label("v2");
  
  ui.newColumn();
  ui.addLabel("Dividers");
  ui.addDivider();
  ui.addDivider();
  ui.addDivider();
  
  ui.addLabel("Labels"); ui.newRow();
  ui.addLabel("small").small(); ui.newRow();
  ui.addLabel("medium").medium(); ui.newRow();
  ui.addLabel("large").large();
  
  ui.addDivider();
  ui.addLabel("Keys"); ui.newRow();
  ui.addLabel("space: toggle UI").small();
  ui.addLabel("n: next theme").small();
  ui.addLabel("b: previous theme").small();
  ui.addLabel("g: toggle grid").small();

  // setup sketch
  for (int i=0; i<numBalls; i++) {
    balls[i] = new Ball();
  }
}

void draw() {
  background(255);
  for (int i=0; i<numBalls; i++) {
    balls[i].draw();
  }
  
  //println(b2Value);
  //println(t2Value);
  //println(s2Value);
}

// callback for b1
void buttonClick(Button b) {
  println("b1 clicked");
  s.value(50);
}

// callback for t1
void toggleToggled(Toggle t) {
  println("t1 toggled. value=" + t.isPressed());
}

// callback for s1
void sliderValue(Slider s) {
  println("s1 value=" + s.value());
}

// callback for s1
void vSliderValue(float v) {
  println("v1 value=" + v);
}

// callback for all mouseEvents in b3
void mouseEvent(MouseEvent e) {
  println("mouseEvent: " + e);
}

// override how b4 is drawn here
void drawEvent(Button b) {
  //println("drawEvent: " + b); 
}

