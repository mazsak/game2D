package core.character;

import core.terrain.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class CharacterImpl implements Character {

    private static final Image img = new ImageIcon("texture\\character\\character.png").getImage();
    private final List<Tile> tiles;
    private final int width = 16, height = 27;
    private int[] anim = {0, 1, 2, 1};
    private int frame = 2;
    private boolean mirror = false;
    private int movingH = 0;
    private int movingV = 0;
    private int x = 150, y = 0;
    private int speed = 3;

    public CharacterImpl(List<Tile> tiles) {
        this.tiles = tiles;
    }

    @Override
    public void top() {
        movingV = 3;
    }

    @Override
    public void right() {
        movingH = speed;
        mirror = true;
    }

    @Override
    public void bottom() {
        movingV = -speed;
    }

    @Override
    public void left() {
        movingH = -speed;
        mirror = false;
    }

    @Override
    public void stopV() {
        movingV = 0;
        if (movingH == 0) {
            frame = 2;
        }
    }

    @Override
    public void stopH() {
        movingH = 0;
        if (movingV == 0) {
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
    public boolean canGo(int dx, int dy) {
        for (Tile p : tiles)
            if (p.getBounds().intersects(x + dx, y + dy, width, height)) {
                return false;
            }
        return true;
    }

    @Override
    public void collide(int dx, int dy) {
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
    public void tick() {
        if (movingH != 0 || movingV != 0) {// animacja ruchu
            frame++;
            while (frame >= anim.length)
                frame -= anim.length;
        }
        // przesuniecie w poziomie
        for (int i = 0; i < Math.abs(movingH); ++i) {
            collide((int) Math.signum(movingH), 0);
            x += (int) Math.signum(movingH);
        }

        // przesuniecie w pionie
        for (int i = 0; i < Math.abs(movingV); ++i) {
            collide(0, -(int) Math.signum(movingV));
            y -= (int) Math.signum(movingV);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x + (mirror ? width : 0), y, x + (mirror ? 0 : width), y + height,
                anim[frame] * width, 0, anim[frame] * width + width, height, null);
    }
}
