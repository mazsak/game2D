package ui;

import core.AnimationController;
import core.character.Character;
import core.character.CharacterImpl;
import core.network.Client;
import core.network.Server;
import ui.play.Terrain;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {

    private final List<Character> characters;
    private final List<Character> charactersThroughNet;
    private final Terrain terrain;
    private final Client client;
    private final Server server;

    public Game() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);

        characters = new ArrayList<>();
        charactersThroughNet = new ArrayList<>();
        characters.add(new CharacterImpl(0));
        terrain = new Terrain("map.txt", characters, charactersThroughNet);
        add(terrain);
        client = null;
        server = new Server(this);
        server.start();
        new Thread(new AnimationController(this)).start();
        //        new Thread(new GamepadBind(character)).start();

        setVisible(true);
    }

    public Game(String address) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);

        characters = new ArrayList<>();
        charactersThroughNet = new ArrayList<>();
        characters.add(new CharacterImpl(0));
        client = new Client(address, this);
        server = null;
        new File("mapWithServer.txt");
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("mapWithServer.txt");
            myWriter.write(client.getMap());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        terrain = new Terrain("mapWithServer.txt", characters, charactersThroughNet);
        add(terrain);
        new Thread(new AnimationController(this)).start();
        //        new Thread(new GamepadBind(character)).start();

        setVisible(true);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public List<Character> getCharactersThroughNet() {
        return charactersThroughNet;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Client getClient() {
        return client;
    }

    public void addPlayer() {
        characters.add(new CharacterImpl(characters.size()));
    }

    public void addPlayerNet(InetAddress address) {
        charactersThroughNet.add(new CharacterImpl(address));
    }

    public void removePlayerNet(InetAddress address) {
        charactersThroughNet.removeIf(character -> character.getAddress().equals(address));
    }

    public void updateData(String dataString, InetAddress address) {
        System.out.println(dataString);
        for (Character character : charactersThroughNet) {
            if (address.equals(character.getAddress())) {
                character.updateData(dataString);
            }
        }
    }

    public byte[] getData() {
        StringBuilder data = new StringBuilder();
        for (Character character : characters) {
            data.append(character.toJson());
        }
        return data.toString().getBytes();
    }

}
