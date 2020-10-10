package core.terrain.tiles;

import core.character.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final int SIZE = 40;

    private List<Image> images;
    private int x, y;
    private int width, height;

    public Tile(int x, int y, String file) {
        this.x = x;
        this.y = y;
        images = new ArrayList<>();
        images.add(new ImageIcon(file).getImage().getScaledInstance(SIZE, SIZE, java.awt.Image.SCALE_SMOOTH));
        width = SIZE;
        height = SIZE;
    }

    public void addImage(String file){
        Image temp = new ImageIcon(file).getImage().getScaledInstance(SIZE, SIZE, java.awt.Image.SCALE_SMOOTH);
        images.add(temp);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width , height);
    }

    public void draw(Graphics g) {
        for (Image img : images)
            g.drawImage(img, x, y, null);
    }

    public void tick() {
    }

    public void collisionV(Character character) {
    }

    public void collisionH(Character character) {
    }
}
