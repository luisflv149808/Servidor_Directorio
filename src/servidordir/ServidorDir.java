package servidordir;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorDir {

    Socket socket;
    ServerSocket serverSocket;

    public static void main(String[] args) {

        Socket socket = null;
        ServerSocket serverSocket = null;

        if (args.length == 1) {

            if (Metodos.Numerico(args[0])) {

                while (true) {

                    try {
                        serverSocket = new ServerSocket(Integer.parseInt(args[0]));
                        socket = serverSocket.accept();
                    } catch (IOException | NumberFormatException e) {
                        System.out.println("Error al crear el servidor " + e);
                        System.exit(1);
                    }

                    BufferedReader lector = null;
                    try {
                        lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException ex) {
                        System.out.println("Error al crear el lector " + ex);
                        System.exit(2);
                    }

                    String entrada = "";
                    PrintWriter escritor = null;
                    try {
                        escritor = new PrintWriter(socket.getOutputStream(), true);
                    } catch (IOException ex) {
                        System.out.println("Error al crear el escritor " + ex);
                        System.exit(3);
                    }
                    while (!serverSocket.isClosed()) {
                        try {
                            if ((entrada = lector.readLine()) != null) {
                                
                                Metodos.EnviarDirectorio(entrada, escritor);
                                serverSocket.close();
                                socket.close();
                            }
                        } catch (IOException ex) {
                            System.out.println("Error al enviar" + ex);
                            System.exit(4);
                        }
                    }
                }

            } else {
                System.out.println("Ingrese un puerto válido");
            }
        } else {
            System.out.println("!!Error!!. Ingresa un puerto");
        }

    }

}
