package core.terrain.tiles;

import core.character.Character;

public class TileWater extends Tile {

    public TileWater(int x, int y, String file) {
        super(x, y, file);
    }

    @Override
    public void collisionV(Character character) {
        character.stopV();
    }

    @Override
    public void collisionH(Character character) {
        character.stopH();
    }
}
