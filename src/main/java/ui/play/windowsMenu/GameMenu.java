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
    int screenWidth, screenHeight;

    public GameMenu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth() / 6;
        screenHeight = (int) screenSize.getHeight() / 6;
        setBounds(2 * screenWidth, screenHeight, 2 * screenWidth, 4 * screenHeight);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(new Color(0, 0, 0, 123));
        createMenu();
        bind();

        setFocusable(true);
    }

    private void createMenu() {
        JLabel title = new JLabel("Menu");
        int x = 40, y = 20, width = 2*(screenWidth-x), height = 100, nextComponent = y+height;
        title.setBounds(x, y, width, height);
        add(title);
        y += nextComponent;
        returnButton.setBounds(x, y, width, height);
        add(returnButton);
        y += nextComponent;
        saveButton.setBounds(x, y, width, height);
        add(saveButton);
        y += nextComponent;
        optionsButton.setBounds(x, y, width, height);
        add(optionsButton);
        y += nextComponent;
        exitButton.setBounds(x, y, width, height);
        add(exitButton);
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

        returnButton.addActionListener(e -> {
            screenTerrain.bind();
            screenTerrain.setFocusable(true);
            screenTerrain.remove(GameMenu.this);
            screenTerrain.repaint();
        });

        saveButton.addActionListener(e -> {
            final SaveWindow saveWindow = new SaveWindow();
            screenTerrain.add(saveWindow);
            screenTerrain.setComponentZOrder(saveWindow, 0);
            screenTerrain.remove(GameMenu.this);
            screenTerrain.repaint();
        });

        optionsButton.addActionListener(e -> {
            final OptionsWindow optionsWindow = new OptionsWindow();
            screenTerrain.add(optionsWindow);
            screenTerrain.setComponentZOrder(optionsWindow, 0);
            screenTerrain.remove(GameMenu.this);
            screenTerrain.repaint();
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}
