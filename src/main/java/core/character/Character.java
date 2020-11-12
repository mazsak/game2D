package core.character;

import core.terrain.tiles.Tile;
import ui.play.Terrain;

import java.awt.*;
import java.net.InetAddress;
import java.util.List;


public interface Character {

    int getX();
    int getY();
    float getHp();
    int getIndexGamepad();
    InetAddress getAddress();
    long getUpdateHp();
    boolean isShowHp();
    long getShowHpTime();
    int getFrame();
    boolean isMirror();
    int getMovingH();
    int getMovingV();

    void setX(int x);
    void setY(int y);

    void movingVertical(int speed);
    void movingHorizontally(int speed);

    void isStop();
    void stopMove();

    boolean cross(Point point);

    boolean canGo(int dx, int dy, List<Tile> tiles);
    void collide(int dx, int dy,List<Tile> tiles);
    void tick(List<Tile> tiles);
    void paint(Graphics g);

    String toJson();
    void updateData(String jsonString);

    void interaction(Terrain terrain);
}
