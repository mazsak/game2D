package core.terrain.tiles;

import constant.Const;
import core.character.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends JPanel {

    private List<Image> images;
    private int x, y;
    private int width, height;

    public Tile(int x, int y, String file) {
        this.x = x;
        this.y = y;
        images = new ArrayList<>();
        images.add(new ImageIcon(file).getImage().getScaledInstance(Const.SIZETILE, Const.SIZETILE, java.awt.Image.SCALE_SMOOTH));
        width = Const.SIZETILE;
        height = Const.SIZETILE;
        setBounds(this.x, this.y, Const.SIZETILE, Const.SIZETILE);
        setVisible(true);
    }

    public void addImage(String file){
        Image temp = new ImageIcon(file).getImage().getScaledInstance(Const.SIZETILE, Const.SIZETILE, java.awt.Image.SCALE_SMOOTH);
        images.add(temp);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width , height);
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
