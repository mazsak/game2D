package ui;

import core.AnimationController;
import core.character.Character;
import core.character.CharacterImpl;
import ui.play.Terrain;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {

    private final List<Character> characters;
    private final Terrain terrain;

    public List<Character> getCharacters() {
        return characters;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Game() {
        setSize(1600, 1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);

        characters = new ArrayList<>();
        characters.add(new CharacterImpl(0));
        terrain = new Terrain("map.txt", characters);
        add(terrain);

        new Thread(new AnimationController(this)).start();
        //        new Thread(new GamepadBind(character)).start();

        setVisible(true);
    }

    public void addPlayer(){
        characters.add(new CharacterImpl(characters.size()));
    }

}
