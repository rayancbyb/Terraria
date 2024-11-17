package terraria;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tronco extends Bloque {
    public Tronco(int x, int y, int tamaño) {
        super(x, y, tamaño, "Tronco", cargarImagen(), 3); // 3 segundos de resistencia, puedes ajustar este valor según tu necesidad
    }

    private static Image cargarImagen() {
        try {
            return ImageIO.read(new File("imagenes/oaklog.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de tronco.");
            return null;
        }
    }
}
