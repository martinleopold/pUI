import com.martinleopold.pui.*;

/*
  testing themes and widgets
*/
PUI ui;

boolean b2Value;
boolean t2Value;
float s2Value;

void setup() {
  size(500, 500);
  
  ui = PUI.init(this);  
//  ui.grid(20,10); // set grid size
//  ui.padding(0.25, 0.25); // set padding (in grid units)
//  ui.font("Monospaced"); // set font
  
  ui.addLabel("Buttons");
  ui.newRow();
  ui.addButton().label("b1").onClick("buttonClick");
  ui.addButton().label("b2").connect("b2Value");
  ui.addButton().label("b3").onMouse("mouseEvent");
  ui.addButton().label("b4").onDraw("drawEvent");
  ui.addDivider();
  
  ui.addLabel("Toggles");
  ui.newRow();
  ui.addToggle().label("t1").onToggle("toggleToggled");
  ui.addToggle().label("t2").connect("t2Value");
  ui.addToggle().label("t3");
  ui.addToggle().label("t4");
  ui.addDivider();
  
  ui.addLabel("Sliders");
  ui.addSlider().label("s1").onValue("sliderValue");
  ui.addSlider().label("s2").connect("s2Value");
  
  ui.addDivider();
  ui.addLabel("Dividers");
  ui.addDivider();
  ui.addDivider();
  
  ui.newColumn();
  ui.addLabel("Labels"); ui.newRow();
  ui.addLabel("small").small(); ui.newRow();
  ui.addLabel("medium").medium(); ui.newRow();
  ui.addLabel("large").large();
  
  ui.addDivider();
  ui.addLabel("Keys");
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
}

// callback for t1
void toggleToggled(Toggle t) {
  println("t1 toggled. value=" + t.on);
}

// callback for s1
void sliderValue(Slider s) {
  println("s1 value=" + s.value);
}

// callback for all mouseEvents in b3
void mouseEvent(MouseEvent e) {
  println("mouseEvent: " + e);
}

// override how b4 is drawn here
void drawEvent(Button b) {
  //println("drawEvent: " + b);
}




