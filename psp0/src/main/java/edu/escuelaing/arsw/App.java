package edu.escuelaing.arsw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase principal para contar líneas de código en archivos fuente.
 * Proporciona funcionalidad para contar líneas físicas y lógicas (excluyendo comentarios).
 */
public class App {
    
    /**
     * Método principal que inicia la aplicación.
     * @param args Argumentos de línea de comandos:
     *             args[0] - Modo de operación ("phy" o "loc")
     *             args[1] - Ruta del archivo a analizar
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java edu.escuelaing.arsw.ContadorLineas <phy|loc> <archivo>");
            System.out.println("  phy - Contar líneas físicas");
            System.out.println("  loc - Contar líneas lógicas (excluyendo comentarios y líneas vacías)");
            return;
        }

        String modo = args[0];
        String rutaArchivo = args[1];

        try {
            int contadorLineas;
            if ("phy".equalsIgnoreCase(modo)) {
                contadorLineas = contarLineasFisicas(rutaArchivo);
                System.out.println("Líneas físicas: " + contadorLineas);
            } else if ("loc".equalsIgnoreCase(modo)) {
                contadorLineas = contarLineasLogicas(rutaArchivo);
                System.out.println("Líneas lógicas: " + contadorLineas);
            } else {
                System.out.println("Modo no válido. Use 'phy' o 'loc'.");
            }
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    /**
     * Cuenta todas las líneas físicas en un archivo, incluyendo comentarios y líneas vacías.
     * @param rutaArchivo Ruta del archivo a analizar
     * @return Número total de líneas físicas
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public static int contarLineasFisicas(String rutaArchivo) throws IOException {
        int contador = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            while (lector.readLine() != null) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Cuenta líneas lógicas de código, excluyendo comentarios y líneas vacías.
     * @param rutaArchivo Ruta del archivo a analizar
     * @return Número de líneas lógicas de código
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public static int contarLineasLogicas(String rutaArchivo) throws IOException {
        int contador = 0;
        boolean bloqueComentado = false;

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (linea.isEmpty()) continue;

                if (bloqueComentado) {
                    if (linea.contains("*/")) {
                        bloqueComentado = false;
                    }
                    continue;
                }

                if (linea.startsWith("//")) continue;
                if (linea.startsWith("/*")) {
                    if (!linea.contains("*/")) {
                        bloqueComentado = true;
                    }
                    continue;
                }

                if (linea.contains("/*")) {
                    bloqueComentado = true;
                    continue;
                }

                contador++;
            }
        }

        return contador;
    }
}