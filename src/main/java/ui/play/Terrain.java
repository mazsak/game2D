package ui.play;

import core.bind.BindKeyboardAdapter;
import core.character.Character;
import core.terrain.TerrainBuilder;
import core.terrain.TerrainBuilderImpl;
import core.terrain.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Terrain extends JPanel {

    private List<Tile> tiles;
    private final List<Character> characters;

    public Terrain(String mapFile, List<Character> characters) {
        setSize(1000, 1000);

        bind(characters.get(0));

        setFocusable(true);
        TerrainBuilderImpl builder = new TerrainBuilderImpl();
        createTerrain(mapFile, builder);
        tiles = builder.getTerrainList();
        this.characters = characters;

    }

    public List<Tile> getTiles() {
        return tiles;
    }

    private void bind(Character character) {
        addKeyListener(new BindKeyboardAdapter(character));
    }

    private void createTerrain(String mapFile, TerrainBuilder terrainBuilder) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(mapFile));
            String line;
            String type;
            int quantity;
            if ((line = br.readLine()) != null) {
                terrainBuilder.setSize(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));
                while ((line = br.readLine()) != null) {
                    String[] lineParts = line.split(";");
                    for (String part : lineParts) {
                        type = part.split("\\.")[0];
                        quantity = Integer.parseInt(part.split("\\.")[1]);
                        switch (type) {
                            case "g":
                                terrainBuilder.buildGrass(quantity);
                                break;
                            case "w":
                                terrainBuilder.buildWater(quantity);
                                break;
                        }
                    }
                }
            }
            terrainBuilder.build();
        } catch (IOException e) {
            System.out.println("Error loading map!");
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        tiles.forEach(tile -> tile.draw(g));
        characters.forEach(character -> character.paint(g));
    }
}
