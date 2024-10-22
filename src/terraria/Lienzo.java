package terraria;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas {
    private Personaje personaje;
    private Image imagenDerecha;
    private Image imagenIzquierda;
    private Image fondo; // Imagen del fondo

    public Lienzo(Personaje personaje) {
        this.personaje = personaje;
        try {
            imagenDerecha = ImageIO.read(new File("imagenes/personajeRight.png")); // Ruta a la imagen para la derecha
            imagenIzquierda = ImageIO.read(new File("imagenes/personajeLeft.png")); // Ruta a la imagen para la izquierda
            fondo = ImageIO.read(new File("imagenes/fondo.png")); // Ruta a la imagen del fondo
        } catch (IOException e) {
            System.out.println("No se pudo cargar una de las imágenes.");
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this); // Dibujar el fondo
        }
        if (personaje.getDireccion() == 'd' && imagenDerecha != null) {
            g.drawImage(imagenDerecha, personaje.getX(), personaje.getY(), 50, 50, this);
        } else if (personaje.getDireccion() == 'a' && imagenIzquierda != null) {
            g.drawImage(imagenIzquierda, personaje.getX(), personaje.getY(), 50, 50, this);
        } else {
            g.fillRect(personaje.getX(), personaje.getY(), 50, 50);
        }
    }
}
