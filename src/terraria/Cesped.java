package terraria;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cesped extends Bloque {
    public Cesped(int x, int y, int tamaño) {
        super(x, y, tamaño, "Cesped", cargarImagen());
    }

    private static Image cargarImagen() {
        try {
            return ImageIO.read(new File("imagenes/grassblock.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de cesped.");
            return null;
        }
    }
}
