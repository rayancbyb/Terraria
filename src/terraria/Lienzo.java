package terraria;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lienzo extends Canvas implements KeyListener {
    private Personaje personaje;
    private Image imagenDerecha;
    private Image imagenIzquierda;
    private Image fondo;
    private Bloque[] suelo;
    private Bloque[] troncos;
    private Bloque[] hojas;
    private Bloque bloqueSeleccionado;
    private Bloque bloqueARomper;
    
    private int tiempoRompiendo; // Contador del tiempo presionando el espacio
    private Timer temporizadorRomper; // Temporizador para medir el tiempo

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
        bloqueSeleccionado = null; // Iniciar sin bloque seleccionado

        // Inicializar temporizador para romper bloques
        tiempoRompiendo = 0;
        temporizadorRomper = new Timer(1000, new ActionListener() { // Se ejecuta cada 1 segundo
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRompiendo++; // Incrementar el tiempo de rotura cada segundo
                System.out.println("Tiempo rompiendo: " + tiempoRompiendo);
            }
        });
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
        int troncosPorPila = 2; // Número de troncos por pila
        troncos = new Bloque[numPilas * troncosPorPila];
        hojas = new Bloque[numPilas * 4]; // Añadir espacio para las hojas: una encima y dos a los lados
        int altoBloque = 50;
        
        for (int i = 0; i < numPilas; i++) {
            int x = i * altoBloque * 5; // Espaciado entre pilas
            for (int j = 0; j < troncosPorPila; j++) {
                troncos[i * troncosPorPila + j] = new Tronco(x, 550 - (j + 1) * altoBloque, altoBloque);
            }
            // Añadir hoja encima del último tronco
            hojas[i * 4] = new Hojas(x, 550 - (troncosPorPila + 1) * altoBloque, altoBloque);
            hojas[i * 4+1] = new Hojas(x, 550 - (troncosPorPila + 2) * altoBloque, altoBloque);

            // Añadir hojas a los lados del último tronco
            hojas[i * 4 + 2] = new Hojas(x - altoBloque, 550 - (troncosPorPila+1 ) * altoBloque, altoBloque);
            hojas[i * 4 + 3] = new Hojas(x + altoBloque, 550 - (troncosPorPila +1) * altoBloque, altoBloque);
        }
    }
    

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        for (Bloque bloque : suelo) {
            if (bloque != null) { // Asegurarse de no dibujar bloques eliminados
                bloque.dibujar(g);
            }
        }
        for (Bloque tronco : troncos) {
            if (tronco != null) { // Asegurarse de no dibujar bloques eliminados
                tronco.dibujar(g);
            }
        }
        for (Bloque hoja : hojas) {
            if (hoja != null) { // Asegurarse de no dibujar bloques eliminados
                hoja.dibujar(g);
            }
        }
        if (personaje.getDireccion() == 'd' && imagenDerecha != null) {
            g.drawImage(imagenDerecha, personaje.getX(), personaje.getY(), 50, 50, this);
        } else if (personaje.getDireccion() == 'a' && imagenIzquierda != null) {
            g.drawImage(imagenIzquierda, personaje.getX(), personaje.getY(), 50, 50, this);
        } else {
            g.fillRect(personaje.getX(), personaje.getY(), 50, 50);
        }
        // Dibujar borde negro alrededor del bloque seleccionado
        if (bloqueSeleccionado != null) {
            g.setColor(Color.BLACK);
            g.drawRect(bloqueSeleccionado.getX(), bloqueSeleccionado.getY(), bloqueSeleccionado.getTamaño(), bloqueSeleccionado.getTamaño());
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
        } else if (key == KeyEvent.VK_SPACE) {
            iniciarRomperBloque();
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            moverSeleccion(key);
        }
        personaje.reducirHambre(1);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            finalizarRomperBloque();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se necesita implementar
    }

    private void moverSeleccion(int key) {
        int index = -1;
        if (bloqueSeleccionado != null) {
            // Buscar índice del bloque seleccionado
            for (int i = 0; i < troncos.length; i++) {
                if (troncos[i] == bloqueSeleccionado) {
                    index = i;
                    break;
                }
            }
        }
        if (key == KeyEvent.VK_LEFT) {
            if (index > 0) {
                bloqueSeleccionado = troncos[index - 1];
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if (index < troncos.length - 1) {
                bloqueSeleccionado = troncos[index + 1];
            }
        } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            // Añadir lógica si es necesario para mover arriba/abajo
        }
    }

    private void iniciarRomperBloque() {
        if (bloqueSeleccionado != null && !temporizadorRomper.isRunning()) {
            bloqueARomper = bloqueSeleccionado;
            tiempoRompiendo = 0; // Reiniciar el contador de tiempo
            temporizadorRomper.start(); // Iniciar el temporizador para medir el tiempo de rotura
        }
    }
    
    private void finalizarRomperBloque() {
        if (bloqueARomper != null && temporizadorRomper.isRunning()) {
            temporizadorRomper.stop(); // Detener el temporizador
            System.out.println("Tiempo total rompiendo: " + tiempoRompiendo + " segundos.");
    
            bloqueARomper.romper(tiempoRompiendo); // Reduce la durabilidad del bloque
            System.out.println("Durabilidad restante del bloque: " + bloqueARomper.getDurabilidadActual());
    
            if (bloqueARomper.estaDestruido()) {
                System.out.println("El bloque ha sido destruido.");
                eliminarBloque(bloqueARomper);
            } else {
                System.out.println("El bloque aún no está destruido.");
            }
            bloqueARomper = null; // Resetea el bloque seleccionado para el siguiente intento
        }
    }

    private void eliminarBloque(Bloque bloque) {
        // Elimina el bloque de cualquier lista donde esté (troncos, hojas, suelo, etc.)
      
        for (int i = 0; i < troncos.length; i++) {
            if (troncos[i] == bloque) {
                // Desplaza los elementos hacia la izquierda
                for (int j = i; j < troncos.length - 1; j++) {
                    troncos[j] = troncos[j + 1]; // Mueve el siguiente elemento a la posición actual
                }
                troncos[troncos.length - 1] = null; // Opcional: Limpia el último elemento
                break; // Salimos del bucle después de eliminar el bloque
            }
        }
    
        
        repaint(); // Actualiza el lienzo después de eliminar el bloque
    }
}
