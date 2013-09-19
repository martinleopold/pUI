import com.martinleopold.pui.*;

/*
  testing themes and widgets
*/

int numBalls = 10;
Ball[] balls = new Ball[numBalls];
PUI ui;

void setup() {
  size(230, 500);
  
  ui = new PUI(this);
  ui.showBackground();
  ui.setTheme(Theme.DEFAULT);
  
  //ui.showGrid();
  //ui.columnWidth(250);
  
  Label l1 = new Label(ui, 0,0, 100, 25); l1.setText("Label");
  ui.newRow();
  Button b1 = new Button(ui, 0,0, 100, 25); b1.setLabel("button");
  Toggle t1 = new Toggle(ui, 0,0, 100, 25); t1.setLabel("toggle");
  Divider d1 = new Divider(ui, 0,0, 210, 25);
  Slider s1 = new Slider(ui, 0,0, 210, 25); s1.setLabel("slider");

  for (int i=0; i<numBalls; i++) {
    balls[i] = new Ball();
  }
}

void draw() {
  background(255);
  for (int i=0; i<numBalls; i++) {
    balls[i].draw();
  }
}

int theme = 0;
void keyPressed() {
  switch (key) {
    case ' ':
      ui.toggle();
      break;
    case 'n':
      if (++theme >= Theme.ALL.length) theme = 0;
      ui.setTheme(Theme.ALL[theme]);
      println(theme);
      break;
    case 'b':
      if (--theme < 0) theme = Theme.ALL.length-1;
      ui.setTheme(Theme.ALL[theme]);
      println(theme);
      break;
  }
}

class Ball {
  float x, y, w, h, g;
  
  Ball() {
    x = random(width);
    y = random(height);
    w = random(0.5*width, width);
    h = w;
    g = random(64, 192);
  }
  
  void draw() {
    w += random(-1, 1);
    h += random(-1, 1);
    x += random(-1, 1);
    y += random(-1, 1);
    fill(g+=random(-1,1));
    ellipse(x, y, w, h);
  }
}
