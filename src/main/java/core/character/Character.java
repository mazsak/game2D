package core.character;

import java.awt.*;


public interface Character {

    void top();
    void right();
    void bottom();
    void left();

    void stopV();
    void stopH();
    void stopMove();

    boolean canGo(int dx, int dy);
    void collide(int dx, int dy);
    void tick();
    void draw(Graphics g);
}
