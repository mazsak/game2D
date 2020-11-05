package core.character;

import core.terrain.tiles.Tile;

import java.awt.*;
import java.net.InetAddress;
import java.util.List;


public interface Character {

    int getX();
    int getY();
    int getIndexGamepad();
    InetAddress getAddress();

    void setX(int x);
    void setY(int y);

    void movingVertical(int speed);
    void movingHorizontally(int speed);

    void isStop();
    void stopMove();

    boolean canGo(int dx, int dy, List<Tile> tiles);
    void collide(int dx, int dy,List<Tile> tiles);
    void tick(List<Tile> tiles);
    void paint(Graphics g);
}
