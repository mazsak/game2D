package core.terrain.tiles;

import constant.Const;

import java.util.Random;

public class TileGrass extends Tile {

    public TileGrass(int x, int y) {
        super(x, y, Const.SIZETILE, Const.SIZETILE, "texture\\grass\\" + Const.textureGrass.get(new Random().nextInt(Const.textureGrass.size())));
    }
}
