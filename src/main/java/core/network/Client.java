package core.network;

import lombok.SneakyThrows;
import ui.Game;

import java.io.IOException;
import java.net.*;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    private final Game game;

    private byte[] buf = new byte[1024];

    public Client(String address, Game game) throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        this.address = InetAddress.getByName(address);
        this.game = game;
    }

    public String getMap() throws IOException {
        buf = "get map".getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        buf = new byte[1024];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        game.addPlayerNet(address);
        return received;
    }

    public void updateData() throws IOException {
        buf = game.getData();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        buf = new byte[1024];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        game.updateData(received, address);
    }

    public void close() {
        socket.close();
    }
}
