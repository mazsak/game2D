package core;


import core.character.Character;
import core.terrain.tiles.Tile;

import javax.swing.*;
import java.util.List;

public class AnimationController implements Runnable {

    private final Character character;
    private final List<Tile> terrain;
    private final JPanel screenGame;

    public AnimationController(Character character, List<Tile> terrain, JPanel screenGame) {
        this.character = character;
        this.terrain = terrain;
        this.screenGame = screenGame;
    }

    @Override
    public void run() {
        new Thread(new GamepadBind(character)).start();
        while (true) {
            character.tick();
            for (Tile tile : terrain)
                tile.tick();
            screenGame.repaint();
            Thread.yield();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
