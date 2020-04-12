package src;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketConnection {
    private final int PORT = 1234; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected String message; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    protected DataOutputStream serverOutput, clientOutput; //Flujo de datos de salida

    public SocketConnection(Boolean isServer) throws IOException {
        if(isServer) {
            ss = new ServerSocket(PORT);//Se crea el socket para el servidor en puerto 1234
            cs = new Socket(); //Socket para el cliente
        } else {
            cs = new Socket(HOST, PORT); //Socket para el cliente en localhost en puerto 1234
        }
    }
}