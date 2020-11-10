package core.network;

import lombok.SneakyThrows;
import ui.Game;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server extends Thread {

    private final Game game;
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[1024];

    @SneakyThrows
    public Server(Game game) {
        socket = new DatagramSocket(4445);
        this.game = game;
    }

    @SneakyThrows
    public void run() {
        running = true;

        while (running) {
            buf = new byte[1024];
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            String received
                    = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("get map")) {
                buf = game.getTerrain().packTerrain().getBytes();
                game.addPlayerNet(address);
            }else if (received.equals("delete player")){
                game.removePlayerNet(address);
                buf = "closed connection".getBytes();
            }else{
                game.updateData(received, address);
                buf = new byte[1024];
                buf = game.getData();
            }
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
        }
        socket.close();
    }
}