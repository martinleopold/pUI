int numBalls = 10;
Ball[] balls = new Ball[numBalls];
int theme = 0;

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

void keyPressed() {
  switch (key) {
    case ' ':
      ui.toggle();
      break;
    case 'n':
      if (++theme >= ui.numThemes()) theme = 0;
      ui.theme(theme);
      println(theme, ui.themeNames()[theme]);
      break;
    case 'b':
      if (--theme < 0) theme = ui.numThemes()-1;
      ui.theme(theme);
      println(theme, ui.themeNames()[theme]);
      break;
    case 'g':
      ui.toggleGrid();
      break;
  }
}
