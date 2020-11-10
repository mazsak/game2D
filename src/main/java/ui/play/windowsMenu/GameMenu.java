package ui.play.windowsMenu;

import ui.Game;
import ui.play.Terrain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameMenu extends JPanel {

    private final Terrain screenTerrain = Game.getInstance().getTerrain();

    private JButton returnButton = new JButton("Return");
    private JButton saveButton = new JButton("Save");
    private JButton optionsButton = new JButton("Options");
    private JButton exitButton = new JButton("Exit");

    public GameMenu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth() / 6;
        int screenHeight = (int) screenSize.getHeight() / 6;
        setBounds(2 * screenWidth, screenHeight, 2 * screenWidth, 4 * screenHeight);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(new Color(0, 0, 0, 123));
        createMenu();
        bind();

        setFocusable(true);
    }

    private void createMenu() {
        add(new JLabel("cos"));
        add(returnButton);
        add(saveButton);
        add(optionsButton);
        add(exitButton);
        System.out.println("tak");
    }

    private void bind() {
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    screenTerrain.bind();
                    screenTerrain.setFocusable(true);
                    screenTerrain.remove(GameMenu.this);
                    screenTerrain.repaint();
                }
            }
        });
        returnButton.setVisible(true);
        returnButton.addActionListener(e -> {
            screenTerrain.bind();
            screenTerrain.setFocusable(true);
            screenTerrain.remove(GameMenu.this);
            screenTerrain.repaint();
        });

        saveButton.setVisible(true);
        saveButton.addActionListener(e -> {
            screenTerrain.remove(GameMenu.this);
            final SaveWindow saveWindow = new SaveWindow();
            screenTerrain.add(saveWindow);
            screenTerrain.setComponentZOrder(saveWindow, 0);
        });

        optionsButton.setVisible(true);
        optionsButton.addActionListener(e -> {
            screenTerrain.remove(GameMenu.this);
            final OptionsWindow optionsWindow = new OptionsWindow();
            screenTerrain.add(optionsWindow);
            screenTerrain.setComponentZOrder(optionsWindow, 0);
        });

        exitButton.setVisible(true);
        exitButton.addActionListener(e -> System.exit(0));
    }
}
