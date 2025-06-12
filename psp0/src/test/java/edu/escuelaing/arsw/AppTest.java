package edu.escuelaing.arsw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    private static final String TEST_FILE_PATH = "src/main/java/edu/escuelaing/arsw/Ejemplo.java";

    @Test
    public void testContarLineasFisicas() throws IOException {
        int expectedLines = 33;
        int actualLines = App.contarLineasFisicas(TEST_FILE_PATH);

        assertEquals(expectedLines, actualLines, "El conteo de líneas físicas no coincide");
    }

    @Test
    public void testContarLineasLogicas() throws IOException {
        int expectedLines = 12;
        int actualLines = App.contarLineasLogicas(TEST_FILE_PATH);

        assertEquals(expectedLines, actualLines, "El conteo de líneas lógicas no coincide");
    }

    @Test
    public void testArchivoSoloConSaltosDeLinea() throws IOException {
        Path file = Files.createTempFile("soloSaltos", ".java");
        Files.writeString(file, "\n\n\n\n");

        int result = App.contarLineasFisicas(file.toString());
        assertEquals(4, result, "Debe contar correctamente las líneas físicas aunque estén vacías");

        Files.deleteIfExists(file);
    }

    @Test
    public void testArchivoConComentariosYCodigo() throws IOException {
        Path file = Files.createTempFile("comentariosYcodigo", ".java");
        String content = "// comentario\npublic class Test {}\n/* comentario largo */";
        Files.writeString(file, content);

        int result = App.contarLineasFisicas(file.toString());
        assertEquals(3, result, "Debe contar todas las líneas físicas");

        Files.deleteIfExists(file);
    }
    
    @Test
    public void testArchivoConCodigoYLineasVacias() throws IOException {
        Path file = Files.createTempFile("codigoYvacio", ".java");
        String content = "public class Test {\n\n    public void m() {}\n\n}";
        Files.writeString(file, content);

        int result = App.contarLineasLogicas(file.toString());
        assertEquals(3, result, "Debe contar solo las líneas lógicas (ignorando vacías)");

        Files.deleteIfExists(file);
    }

    @Test
    public void testArchivoConComentariosMultilinea() throws IOException {
        Path file = Files.createTempFile("comentariosMultilinea", ".java");
        String content = "/*\nComentario\nmultilínea\n*/\npublic class X {}";
        Files.writeString(file, content);

        int result = App.contarLineasLogicas(file.toString());
        assertEquals(1, result, "Debe contar solo la línea de código, no los comentarios");

        Files.deleteIfExists(file);
    }
}
