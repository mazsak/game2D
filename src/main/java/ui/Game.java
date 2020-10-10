package ui;

import ui.play.Terrain;

import javax.swing.*;

public class Game extends JFrame {

    public Game() {
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        add(new Terrain("map.txt"));

        setVisible(true);
    }
}
