package main.java.edu.escuelaing.arsw;
/**
 * Este es un programa de ejemplo que calcula el área de un círculo.
 * Tiene comentarios y líneas en blanco.
 */
public class Ejemplo {

    // Constante para el valor de PI
    private static final double PI = 3.141592;

    /**
     * Método principal que ejecuta el programa
     * @param args
     */
    public static void main(String[] args) {
        double radio = 5.0;
        
        // Calcular y mostrar el área
        double area = calcularArea(radio);
        System.out.println("El área del círculo es: " + area + " m²");
    }

    /**
     * Calcula el área de un círculo dado su radio
     * @param radio El radio del círculo
     * @return El área calculada
     */
    public static double calcularArea(double radio) {
        // Fórmula: área = PI * radio al cuadrado
        return PI * Math.pow(radio, 2);
    }
}
//Este archivo, tiene 12 líneas lógicas y 33 líneas físicas.