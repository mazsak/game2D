package core.character;

import core.terrain.tiles.Tile;

import java.awt.*;
import java.util.List;


public interface Character {

    int getIndexGamepad();

    void movingVertical(int speed);
    void movingHorizontally(int speed);

    void isStop();
    void stopMove();

    boolean canGo(int dx, int dy, List<Tile> tiles);
    void collide(int dx, int dy,List<Tile> tiles);
    void tick(List<Tile> tiles);
    void paint(Graphics g);
}
