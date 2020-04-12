package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import src.SocketClient;

public class MainClient {
    public static void main(String[] args) throws IOException {
        System.out.println("======================");
        System.out.println("Iniciando servidor cliente - usuario");
        System.out.println("======================");
        SocketClient sc = new SocketClient();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        String line;


        showMenu();
        while ((line = in.readLine()) != null) {
            sc.sendMessage(line);
            showMenu();
        }
    }

    private static void showMenu() {
        System.out.println("- Ingrese el numero de cuenta si quiere consultar el saldo ");
        System.out.println("- Ingrese el numero de cuenta y el saldo separado por coma si quiere modificar");
    }
}
