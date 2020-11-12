package core.terrain;

import constant.Const;
import core.flora.Flower;
import core.flora.Plant;
import core.terrain.tiles.Tile;
import core.terrain.tiles.TileGrass;
import core.terrain.tiles.TileWater;

import java.util.*;
import java.util.stream.Collectors;

public class TerrainBuilderImpl implements TerrainBuilder {

    private Map<String, int[][]> masks;
    private StringBuilder mapString;
    private Tile[][] terrain;
    private List<Tile> flora;
    private int x, y;
    private int width, height;

    public TerrainBuilderImpl() {
        x = 0;
        y = 0;
        mapString = new StringBuilder();
        flora = new ArrayList<>();
        masks = new HashMap<>();

        masks.put("top", new int[][]{
                {2, 0, 2},
                {1, 1, 1},
                {2, 2, 2}});
        masks.put("top_right", new int[][]{
                {2, 0, 0},
                {2, 1, 0},
                {2, 2, 2}});
        masks.put("top_right_inv", new int[][]{
                {1, 1, 0},
                {1, 1, 1},
                {1, 1, 1}});
        masks.put("right", new int[][]{
                {1, 1, 2},
                {1, 1, 0},
                {1, 1, 2}});
        masks.put("right_bottom", new int[][]{
                {2, 2, 2},
                {2, 1, 0},
                {2, 0, 0}});
        masks.put("right_bottom_inv", new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 0}});
        masks.put("bottom", new int[][]{
                {2, 2, 2},
                {1, 1, 1},
                {2, 0, 2}});
        masks.put("bottom_left", new int[][]{
                {2, 2, 2},
                {0, 1, 2},
                {0, 0, 2}});
        masks.put("bottom_left_inv", new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {0, 1, 1}});
        masks.put("left", new int[][]{
                {2, 1, 1},
                {0, 1, 1},
                {2, 1, 1}});
        masks.put("left_top", new int[][]{
                {0, 0, 2},
                {0, 1, 2},
                {2, 2, 2}});
        masks.put("left_top_inv", new int[][]{
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 1}});
    }

    public List<Tile> getTerrainList() {
        List<Tile> tiles = flora;
        tiles.addAll(Arrays.stream(terrain)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        return tiles;
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.terrain = new Tile[width][height];
        mapString.append(width).append(" ").append(height).append("\n");
    }

    @Override
    public void buildGrass(int number) {
        for (int i = 0; i < number; i++) {
            Tile grass = new TileGrass(x * Const.SIZETILE, y * Const.SIZETILE);
            terrain[x++][y] = grass;
            if (x == width) {
                x = 0;
                y++;
            }
        }
    }

    @Override
    public void buildWater(int number) {
        for (int i = 0; i < number; i++) {
            Tile water = new TileWater(x * Const.SIZETILE, y * Const.SIZETILE);
            terrain[x++][y] = water;
            if (x == width) {
                x = 0;
                y++;
            }
        }
    }

    @Override
    public void buildFlower(int number) {
        for (int i = 0; i < number; i++) {
            Plant flower = new Flower(x * Const.SIZETILE, y * Const.SIZETILE);
            flora.add(flower);
        }
    }

    @Override
    public void build() {
        buildGrassEdges();
    }

    @Override
    public void addLineToMapString(String line) {
        mapString.append(line).append("\n");
    }

    @Override
    public String pack() {
        return mapString.toString();
    }

    private void buildGrassEdges() {
        Class[] nameClass = {TileGrass.class, TileWater.class, Tile.class};
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                if (checkMask(x, y, nameClass, masks.get("top"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_top.png");
                }
                if (checkMask(x, y, nameClass, masks.get("top_right"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_top_right.png");
                }
                if (checkMask(x, y, nameClass, masks.get("top_right_inv"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_top_right_inv.png");
                }
                if (checkMask(x, y, nameClass, masks.get("right"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_right.png");
                }
                if (checkMask(x, y, nameClass, masks.get("right_bottom"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_right_bottom.png");
                }
                if (checkMask(x, y, nameClass, masks.get("right_bottom_inv"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_right_bottom_inv.png");
                }
                if (checkMask(x, y, nameClass, masks.get("bottom"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_bottom.png");
                }
                if (checkMask(x, y, nameClass, masks.get("bottom_left"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_bottom_left.png");
                }
                if (checkMask(x, y, nameClass, masks.get("bottom_left_inv"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_bottom_left_inv.png");
                }
                if (checkMask(x, y, nameClass, masks.get("left"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_left.png");
                }
                if (checkMask(x, y, nameClass, masks.get("left_top"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_left_top.png");
                }
                if (checkMask(x, y, nameClass, masks.get("left_top_inv"))) {
                    terrain[x][y].addImage("texture\\grass\\grass_left_top_inv.png");
                }
            }
        }
    }

    private boolean checkMask(int x, int y, Class[] nameClass, int[][] mask) {
        return (mask[0][0] == 2 || terrain[x - 1][y - 1].getClass() == nameClass[mask[0][0]]) &&
                (mask[0][1] == 2 || terrain[x][y - 1].getClass() == nameClass[mask[0][1]]) &&
                (mask[0][2] == 2 || terrain[x + 1][y - 1].getClass() == nameClass[mask[0][2]]) &&
                (mask[1][0] == 2 || terrain[x - 1][y].getClass() == nameClass[mask[1][0]]) &&
                (mask[1][1] == 2 || terrain[x][y].getClass() == nameClass[mask[1][1]]) &&
                (mask[1][2] == 2 || terrain[x + 1][y].getClass() == nameClass[mask[1][2]]) &&
                (mask[2][0] == 2 || terrain[x - 1][y + 1].getClass() == nameClass[mask[2][0]]) &&
                (mask[2][1] == 2 || terrain[x][y + 1].getClass() == nameClass[mask[2][1]]) &&
                (mask[2][2] == 2 || terrain[x + 1][y + 1].getClass() == nameClass[mask[2][2]]);
    }
}
