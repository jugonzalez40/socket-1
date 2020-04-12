package src;

import java.io.IOException;

import src.SocketServer;

public class MainServer {
    public static void main(String[] args) throws IOException {
        System.out.println("======================");
        System.out.println("Iniciando servidor socket - cajero");
        System.out.println("======================");
        SocketServer sss = new SocketServer();
        sss.startServer();
    }
}

