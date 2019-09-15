package servidordir;

import java.io.File;
import java.io.PrintWriter;

public class Metodos {

    public static boolean Numerico(String cadena) {

        boolean resultado;
        try {

            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    public static void EnviarDirectorio(String direccion, PrintWriter escritor) {
        File carpeta = new File(direccion);
        if (carpeta.exists()) {
            File[] archivos = carpeta.listFiles();
            if (archivos == null || archivos.length == 0) {
                System.out.println("Carpeta Vacia");
                return;
            } else {

                for (int i = 0; i < archivos.length; i++) {
                    File archivo = archivos[i];
                    escritor.println(String.format(archivo.getName()));
                }
            }
        } else {
            System.out.println("No se encontró la carpeta escrita");
        }
    }

}
