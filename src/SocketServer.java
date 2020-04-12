package src;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import src.SocketConnection;

class SocketServer extends SocketConnection {
    private final String basePath = new File("").getAbsolutePath();
    public final String data = basePath.concat("\\src\\data.txt");

    public SocketServer() throws IOException {
        super(true);
    }

    public void startServer() {
        try {

            while (true) {
                System.out.println("==================");
                System.out.println("Socket Server a la espera"); // Esperando conexión

                cs = ss.accept(); // Accept comienza el socket y espera una conexión desde un cliente

                System.out.println("Cliente en línea");

                
                InputStreamReader in = new InputStreamReader(cs.getInputStream());
                // Se obtiene el flujo entrante desde el cliente
                BufferedReader entrada = new BufferedReader(in);

                while ((message = entrada.readLine()) != null) {
                    // Se muestra por pantalla el mensaje recibido
                    String out = message.trim().split(",").length == 2 ? this.writeFile(message) : this.findAccount(message);
                    PrintWriter pw = new PrintWriter(cs.getOutputStream());
                    pw.println(out);
                    pw.println("end");
                    pw.flush();
                }

                //clientOutput.writeUTF("Fin de la transacción");
                
                // ss.close();// Se finaliza la conexión con el cliente
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String writeFile(String data) {
        try {
            System.out.println("Modificando saldo...");
            Files.write(Paths.get(this.data), data.concat("\n").getBytes(), StandardOpenOption.APPEND);
            return "Registro grabado OK";
        } catch (IOException e) {
            e.printStackTrace();
            return "NO-OK";
        }
    }

    private String findAccount(String accountToFind) throws IOException {
        // We need to provide file path as the parameter:
        // double backquote is to avoid compiler interpret words
        // like \\test as \\t (ie. as a escape sequence)
        System.out.println("Buscando saldo...");
        File file = new File(this.data);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String account[] = st.trim().split(",");
            if (accountToFind.equals(account[0])) {
                return "Su saldo es: $" + account[1];
            }
        }

        return "NO-OK";
    }
}