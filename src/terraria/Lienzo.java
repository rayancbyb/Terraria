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
    private Bloque[] troncos;
    private Bloque[] hojas;

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
        crearTroncosYHojas();
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

    private void crearTroncosYHojas() {
        int numPilas = 2; // Número de pilas de troncos
        int troncosPorPila = 3; // Número de troncos por pila
        troncos = new Bloque[numPilas * troncosPorPila];
        hojas = new Bloque[numPilas * 3]; // Añadir espacio para las hojas: una encima y dos a los lados
        int altoBloque = 50;
        
        for (int i = 0; i < numPilas; i++) {
            int x = i * altoBloque * 5; // Espaciado entre pilas
            for (int j = 0; j < troncosPorPila; j++) {
                troncos[i * troncosPorPila + j] = new Tronco(x, 550 - (j + 1) * altoBloque, altoBloque);
            }
            // Añadir hoja encima del último tronco
            hojas[i * 3] = new Hojas(x, 550 - (troncosPorPila + 1) * altoBloque, altoBloque);
            // Añadir hojas a los lados del último tronco
            hojas[i * 3 + 1] = new Hojas(x - altoBloque, 550 - (troncosPorPila ) * altoBloque, altoBloque);
            hojas[i * 3 + 2] = new Hojas(x + altoBloque, 550 - (troncosPorPila ) * altoBloque, altoBloque);
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
    for (Bloque tronco : troncos) {
        tronco.dibujar(g);
    }
    for (Bloque hoja : hojas) {
        hoja.dibujar(g);
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
