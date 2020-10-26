package ui.play;

import core.character.Character;
import core.AnimationController;
import core.character.CharacterImpl;
import core.terrain.TerrainBuilder;
import core.terrain.TerrainBuilderImpl;
import core.terrain.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Terrain extends JPanel {

    private List<Tile> terrain;
    private Character character;

    public Terrain(String mapFile) {
        setSize(1000, 1000);

        bind();

        setFocusable(true);
        TerrainBuilderImpl builder = new TerrainBuilderImpl();
        createTerrain(mapFile, builder);
        terrain = builder.getTerrainList();

        character = new CharacterImpl(terrain);

        new Thread(new AnimationController(character, terrain, this)).start();
//        new Thread(new GamepadBind(character)).start();

    }

    private void bind(){
        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ev) {
                switch(ev.getKeyCode())	{
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        character.stopH();
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        character.stopV();
                        break;
                    default:
                        System.out.println(ev.getKeyCode());
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println(e.getKeyChar() + " - " + e.getKeyCode());
            }

            public void keyPressed(KeyEvent ev) {
                switch(ev.getKeyCode())	{
                    case KeyEvent.VK_LEFT:	character.left(); break;
                    case KeyEvent.VK_RIGHT:	character.right(); break;
                    case KeyEvent.VK_UP:	character.top(); break;
                    case KeyEvent.VK_DOWN:	character.bottom(); break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(ev.getKeyCode());
                        System.out.println(ev.getKeyChar());
                }
            }

        });
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

    public void paint(Graphics g) {
        super.paint(g);
        for (Tile s : terrain)
            s.draw(g);
        character.draw(g);
    }
}
