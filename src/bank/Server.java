package bank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int port=3333;
    ServerSocket server;

    public Server(){
        try {
            server=new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listen(){
        try {
            while (true) {
                new ServerThread(server.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server=new Server();
        server.listen();
    }

}
