package core;


import core.bind.BindGamepad;
import ui.Game;

import java.io.IOException;

public class AnimationController implements Runnable {

    private final Game game;

    public AnimationController(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        new Thread(new BindGamepad(game)).start();
        while (true) {
            game.getCharacters().forEach(character -> character.tick(game.getTerrain().getTiles()));
            game.getCharactersThroughNet().forEach(character -> character.tick(game.getTerrain().getTiles()));
//            for (Tile tile : game.getTerrain().getTiles())
//                tile.tick();
            game.repaint();
            if (game.getClient() != null) {
                try {
                    game.getClient().updateData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            game.getTerrain().repaint();
            Thread.yield();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
