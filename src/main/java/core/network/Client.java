package core.network;

import lombok.SneakyThrows;
import ui.Game;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    private final Game game;

    private byte[] buf;

    @SneakyThrows
    public Client(String address, Game game) {
        socket = new DatagramSocket();
        this.address = InetAddress.getByName(address);
        this.game = game;
    }

    @SneakyThrows
    public String getMap() {
        buf = "get map".getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }

    @SneakyThrows
    public void updateData() {

        buf = game.getData();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        game.updateData(received);
    }

    public void close() {
        socket.close();
    }
}
