package core.terrain.tiles;

import constant.Const;
import core.character.Character;

import java.util.Random;

public class TileWater extends Tile {

    public TileWater(int x, int y) {
        super(x, y, Const.SIZETILE, Const.SIZETILE, "texture\\water\\" + Const.textureWater.get(new Random().nextInt(Const.textureWater.size())));
    }

    @Override
    public void collisionV(Character character) {
        character.movingVertical(0);
    }

    @Override
    public void collisionH(Character character) {
        character.movingHorizontally(0);
    }
}
