package core.flora;

import constant.Const;
import core.character.Character;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Flower extends Plant {

    private boolean is_close = false;
    private Image action;

    public Flower(int x, int y) {
        super(x + 5, y + 5, Const.SIZEFLOWER, Const.SIZEFLOWER, "texture\\flora\\flower\\" + Const.textureFlower.get(new Random().nextInt(Const.textureFlower.size())));
        action = new ImageIcon("texture\\letters\\letter_E.png").getImage().getScaledInstance(Const.SIZELETTER, Const.SIZELETTER, java.awt.Image.SCALE_SMOOTH);
        setBounds(this.x - 25, this.y - 25, this.width + 50, this.height + 50);
    }

    @Override
    public void collisionV(Character character) {
        super.collisionV(character);
        is_close = true;
    }

    @Override
    public void collisionH(Character character) {
        super.collisionH(character);
        is_close = true;
    }

    @Override
    public void paint(Graphics g) {
        for (Image img : images)
            g.drawImage(img, 10, 10, null);
        if (is_close) {
            g.drawImage(action, 15, -5, null);
            is_close = false;
        }
    }


}
