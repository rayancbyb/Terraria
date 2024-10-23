package terraria;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Hojas extends Bloque {
    public Hojas(int x, int y, int tamaño) {
        super(x, y, tamaño, "Hojas", cargarImagen(), 2); // 2 segundos de resistencia, ajustable según tus necesidades
    }

    private static Image cargarImagen() {
        try {
            return ImageIO.read(new File("imagenes/leaves.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de Hojas.");
            return null;
        }
    }
}
