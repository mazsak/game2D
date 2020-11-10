package ui;

import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientMain {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        new Game("192.168.1.13");
    }
}
