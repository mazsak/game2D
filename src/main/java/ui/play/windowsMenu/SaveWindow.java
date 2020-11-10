package ui.play.windowsMenu;

import ui.Game;
import ui.play.Terrain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.SocketException;

public class SaveWindow extends JPanel {

    private final Terrain screenTerrain = Game.getInstance().getTerrain();

    public SaveWindow() throws SocketException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth() / 6;
        int screenHeight = (int) screenSize.getHeight() / 6;
        setBounds(screenWidth, screenHeight, 4 * screenWidth, 4 * screenHeight);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        bind();
        setFocusable(true);
    }


    private void bind() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    screenTerrain.remove(SaveWindow.this);
                    GameMenu gameMenu = null;
                    try {
                        gameMenu = new GameMenu();
                    } catch (SocketException ex) {
                        ex.printStackTrace();
                    }
                    screenTerrain.add(gameMenu);
                    screenTerrain.setComponentZOrder(gameMenu, 0);
                }
            }
        });
    }

    }
