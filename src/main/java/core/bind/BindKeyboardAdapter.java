package core.bind;

import constant.Const;
import core.character.Character;
import core.terrain.tiles.Tile;
import ui.play.Terrain;
import ui.play.windowsMenu.GameMenu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.SocketException;

public class BindKeyboardAdapter extends KeyAdapter {

    private final Character character;
    private final Terrain screenTerrain;

    public BindKeyboardAdapter(Character character, Terrain terrain) {
        this.character = character;
        this.screenTerrain = terrain;
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
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyReleased(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_E:
                character.interaction(screenTerrain);
                break;
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
                GameMenu gameMenu = null;
                try {
                    gameMenu = new GameMenu();
                } catch (SocketException ex) {
                    ex.printStackTrace();
                }
                character.stopMove();
                screenTerrain.add(gameMenu);
                screenTerrain.setComponentZOrder(gameMenu, 0);
                screenTerrain.setFocusable(false);
                screenTerrain.removeKeyListener(this);
//                screenTerrain.repaint();
                break;
            default:
                System.out.println("Code key: " + e.getKeyCode() + ", Char key: " + e.getKeyChar());
        }
    }
}
