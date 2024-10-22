package terraria;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Lienzo extends Canvas implements KeyListener {
    private Personaje personaje;
    private Image imagenDerecha;
    private Image imagenIzquierda;
    private Image fondo;
    private Bloque[] suelo;

    public Lienzo(Personaje personaje) {
        this.personaje = personaje;
        try {
            imagenDerecha = ImageIO.read(new File("imagenes/personajeRight.png"));
            imagenIzquierda = ImageIO.read(new File("imagenes/personajeLeft.png"));
            fondo = ImageIO.read(new File("imagenes/fondo.jpg"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar una de las imágenes.");
        }
        addKeyListener(this);
        setFocusable(true);
        crearSuelo();
    }

    private void crearSuelo() {
        int anchoPantalla = 900;
        int altoBloque = 50;
        int numBloques = anchoPantalla / altoBloque;
        suelo = new Bloque[numBloques];
        for (int i = 0; i < numBloques; i++) {
            suelo[i] = new Cesped(i * altoBloque, 550, altoBloque); // 550 es la posición y para el suelo
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        for (Bloque bloque : suelo) {
            bloque.dibujar(g);
        }
        if (personaje.getDireccion() == 'd' && imagenDerecha != null) {
            g.drawImage(imagenDerecha, personaje.getX(), personaje.getY(), 50, 50, this);
        } else if (personaje.getDireccion() == 'a' && imagenIzquierda != null) {
            g.drawImage(imagenIzquierda, personaje.getX(), personaje.getY(), 50, 50, this);
        } else {
            g.fillRect(personaje.getX(), personaje.getY(), 50, 50);
        }

        g.setColor(Color.WHITE);
        g.drawString("Salud: " + personaje.getSalud(), 10, 20);
        g.drawString("Hambre: " + personaje.getHambre(), 10, 35);
        g.drawString("Posición: (" + personaje.getX() + ", " + personaje.getY() + ")", 10, 50);
        g.drawString("Experiencia: " + personaje.getExperiencia(), 10, 65);
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
