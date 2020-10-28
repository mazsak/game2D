package core.bind;

import constant.Const;
import core.character.Character;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BindKeyboardAdapter extends KeyAdapter {

    private final Character character;

    public BindKeyboardAdapter(Character character) {
        this.character = character;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyTyped(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                character.movingHorizontally(0);
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                character.movingVertical(0);
                break;
            default:
                System.out.println(e.getKeyCode());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        super.keyPressed(e);
        System.out.println("Pressed key: " + e.getKeyChar() + " - " + e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyReleased(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                character.movingHorizontally(-Const.SPEEDHERO);
                break;
            case KeyEvent.VK_RIGHT:
                character.movingHorizontally(Const.SPEEDHERO);
                break;
            case KeyEvent.VK_UP:
                character.movingVertical(Const.SPEEDHERO);
                break;
            case KeyEvent.VK_DOWN:
                character.movingVertical(-Const.SPEEDHERO);
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            default:
                System.out.println(e.getKeyCode());
                System.out.println(e.getKeyChar());
        }
    }
}
