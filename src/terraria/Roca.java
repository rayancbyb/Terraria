package terraria;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Roca extends Bloque {
    public Roca(int x, int y, int tamaño) {
        super(x, y, tamaño, "Roca", cargarImagen(), 1); // 1 segundo de resistencia, ajustable según tus necesidades
    }

    private static Image cargarImagen() {
        try {
            return ImageIO.read(new File("imagenes/roca.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de roca.");
            return null;
        }
    }
}
