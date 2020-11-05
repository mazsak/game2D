package core.character;

import core.terrain.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.util.List;


public class CharacterImpl implements Character {

    private static final Image img = new ImageIcon("texture\\character\\character.png").getImage();
    private final int width = 16, height = 27;
    private final int indexGamepad;
    private final InetAddress address;
    private int[] anim = {0, 1, 2, 1};
    private int frame = 2;
    private boolean mirror = false;
    private int movingH = 0;
    private int movingV = 0;
    private int x = 150, y = 0;

    public CharacterImpl(int indexGamepad) {
        this.indexGamepad = indexGamepad;
        this.address = null;
    }

    public CharacterImpl(InetAddress address) {
        this.indexGamepad = Integer.parseInt(null);
        this.address = address;
    }

    public int getIndexGamepad() {
        return indexGamepad;
    }

    public InetAddress getAddress() {
        return address;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void movingVertical(int speed) {
        movingV = speed;
        isStop();
    }

    @Override
    public void movingHorizontally(int speed) {
        movingH = speed;
        mirror = speed >= 0;
        isStop();
    }

    @Override
    public void isStop() {
        if (movingH == 0 && movingV == 0) {
            frame = 2;
        }
    }


    @Override
    public void stopMove() {
        movingH = 0;
        movingV = 0;
        frame = 2;
    }

    @Override
    public boolean canGo(int dx, int dy, List<Tile> tiles) {
        for (Tile p : tiles)
            if (p.getBounds().intersects(x + dx, y + dy, width, height)) {
                return false;
            }
        return true;
    }

    @Override
    public void collide(int dx, int dy, List<Tile> tiles) {
        for (Tile p : tiles) {
            if (p.getBounds().intersects(x + dx, y + dy, width, height)) {
                if (dx != 0)
                    p.collisionH(this);
                if (dy != 0)
                    p.collisionV(this);
            }
        }
    }

    @Override
    public void tick(List<Tile> tiles) {
        if (movingH != 0 || movingV != 0) {// animacja ruchu
            frame++;
            while (frame >= anim.length)
                frame -= anim.length;
        }
        // przesuniecie w poziomie
        for (int i = 0; i < Math.abs(movingH); ++i) {
            collide((int) Math.signum(movingH), 0, tiles);
            x += (int) Math.signum(movingH);
        }

        // przesuniecie w pionie
        for (int i = 0; i < Math.abs(movingV); ++i) {
            collide(0, -(int) Math.signum(movingV), tiles);
            y -= (int) Math.signum(movingV);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, x + (mirror ? width : 0), y, x + (mirror ? 0 : width), y + height,
                anim[frame] * width, 0, anim[frame] * width + width, height, null);
    }
}
