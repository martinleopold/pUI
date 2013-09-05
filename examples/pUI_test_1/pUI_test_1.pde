import com.martinleopold.pui.*;

public float val;

void setup() {
  size(200, 200);
  smooth();
  
  PUI ui = new PUI(this);
  
  Dummy dummy = new Dummy(ui, 10,10, 50, 25);
  dummy.onMouse("mouseEvent");
  
  Button b = new Button(ui, 10,50, 50, 25);
  b.onDraw("drawButton");
  b.onButtonClicked("buttonClicked");
  
  Slider s = new Slider(ui, 10,90, 100, 25);
  s.onValueChanged("sliderValue");
  s.connectTo("val");
}

float size = 0.5;
boolean fill = false;

void draw() {
  background(0);
  
  if (fill) fill(90);
  else noFill();
  stroke(90);
  ellipse(width/2, height/2, width*size, height*size);
  
  fill(200);
  rect(0, 180, val*200, 10);
}


public void mouseEvent(MouseEvent e) {
  println("mouse event: " + e.getAction());
}

void sliderValue(Slider s) {
  println("slider value: " + s.value);
  size = s.value;
}

public void drawButton(Button b) {
  stroke(128);
  if (b.pushed) fill(255);
  else fill(128);
  ellipse(10,50, 10, 10);
}

public void buttonClicked(Button b) {
  println("button clicked");
  fill = !fill;
}


