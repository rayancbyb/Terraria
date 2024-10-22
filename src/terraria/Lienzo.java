package terraria;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements KeyListener {
    private Personaje personaje;
    private Image imagenDerecha;
    private Image imagenIzquierda;
    private Image fondo;

    public Lienzo(Personaje personaje) {
        this.personaje = personaje;
        try {
            imagenDerecha = ImageIO.read(new File("imagenes/personajeRight.png"));
            imagenIzquierda = ImageIO.read(new File("imagenes/personajeLeft.png"));
            fondo = ImageIO.read(new File("imagenes/fondo.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar una de las imágenes.");
        }
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        if (personaje.getDireccion() == 'd' && imagenDerecha != null) {
            g.drawImage(imagenDerecha, personaje.getX(), personaje.getY(), 50, 50, this);
        } else if (personaje.getDireccion() == 'a' && imagenIzquierda != null) {
            g.drawImage(imagenIzquierda, personaje.getX(), personaje.getY(), 50, 50, this);
        } else {
            g.fillRect(personaje.getX(), personaje.getY(), 50, 50);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {
            personaje.mover('d');
        } else if (key == KeyEvent.VK_A) {
            personaje.mover('a');
        }
        personaje.reducirHambre(1);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No se necesita implementar
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se necesita implementar
    }
}
