package core.terrain.tiles;

import constant.Const;
import core.character.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends JPanel {

    protected List<Image> images;
    protected int x, y;
    protected int width, height;

    public Tile(int x, int y, int width, int height, String file) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        images = new ArrayList<>();
        images.add(new ImageIcon(file).getImage().getScaledInstance(this.width, this.height, java.awt.Image.SCALE_SMOOTH));
        setBounds(this.x, this.y, this.width, this.height);
        setVisible(true);
    }

    public void addImage(String file){
        Image temp = new ImageIcon(file).getImage().getScaledInstance(Const.SIZETILE, Const.SIZETILE, java.awt.Image.SCALE_SMOOTH);
        images.add(temp);
    }

    @Override
    public void paint(Graphics g) {
        for (Image img : images)
            g.drawImage(img, 0,0, null);
    }

    public void tick() {
    }

    public void collisionV(Character character) {
    }

    public void collisionH(Character character) {
    }
}
