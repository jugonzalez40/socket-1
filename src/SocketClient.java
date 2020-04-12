package src;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import src.SocketConnection;

public class SocketClient extends SocketConnection {
    public SocketClient() throws IOException {
        super(false);
    } // Se usa el constructor para cliente de Conexion

    public void sendMessage(String message) {
        try {
            PrintWriter pw = new PrintWriter(cs.getOutputStream());
            pw.println(message);
            pw.flush();
            

            InputStreamReader in = new InputStreamReader(cs.getInputStream());
            // Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(in);
            String msg;
            while ((msg = entrada.readLine()) != null) {
                if(msg.equals("end")) break;
                // Se muestra por pantalla el mensaje recibido
                System.out.println(msg);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void startClient() throws IOException {

    }
}