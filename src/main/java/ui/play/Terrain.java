package ui.play;

import core.bind.BindKeyboardAdapter;
import core.character.Character;
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
    private final List<Character> charactersThroughNet;
    private final TerrainBuilderImpl builder;

    public Terrain(String mapFile, List<Character> characters, List<Character> charactersThroughNet) {
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);

        this.characters = characters;
        this.charactersThroughNet = charactersThroughNet;
        this.builder = new TerrainBuilderImpl();
        createTerrain(mapFile);
        tiles = builder.getTerrainList();

        tiles.forEach(Terrain.this::add);

        bind();

        setVisible(true);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void bind() {
        addKeyListener(new BindKeyboardAdapter(characters.get(0), this));
    }

    private void createTerrain(String mapFile) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(mapFile));
            String line;
            String type;
            int quantity;
            if ((line = br.readLine()) != null) {
                builder.setSize(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));
                while ((line = br.readLine()) != null) {
                    String[] lineParts = line.split(";");
                    for (String part : lineParts) {
                        type = part.split("\\.")[0];
                        quantity = Integer.parseInt(part.split("\\.")[1]);
                        switch (type) {
                            case "g":
                                builder.buildGrass(quantity);
                                break;
                            case "w":
                                builder.buildWater(quantity);
                                break;
                        }
                    }
                }
            }
            builder.build();
        } catch (IOException e) {
            System.out.println("Error loading map!");
            e.printStackTrace();
        }
    }

    public String packTerrain(){
        return builder.pack();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        tiles.forEach(tile -> tile.draw(g));
        characters.forEach(character -> character.paint(g));
        charactersThroughNet.forEach(character -> character.paint(g));
    }
}
