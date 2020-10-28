package core;


import ui.Game;
import core.terrain.tiles.Tile;

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
            for (Tile tile : game.getTerrain().getTiles())
                tile.tick();
            game.repaint();
            Thread.yield();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}