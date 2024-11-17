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
    private Bloque[] bloques;
    private Bloque bloqueSeleccionado;
    private Bloque bloqueARomper;

        int numPilas = 2; // Reducimos el número de pilas de troncos
    int troncosPorPila = 2; // Número de troncos por pila
    int altoBloque = 20; // Ajustamos el tamaño de los bloques para una pantalla más pequeña
    int anchoPantalla = 640;

    
    private int tiempoRompiendo; // Contador del tiempo presionando el espacio
    private Timer temporizadorRomper; // Temporizador para medir el tiempo

    public Lienzo(Personaje personaje) {
        this.personaje = personaje;
        try {
            imagenDerecha = ImageIO.read(new File("imagenes/personajeRight.png"));
            imagenIzquierda = ImageIO.read(new File("imagenes/personajeLeft.png"));
            fondo = ImageIO.read(new File("imagenes/fondo.jpeg"));
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
        int numBloques = 40; // Ajustado para la nueva anchura de la pantalla

        // Total de bloques: césped, rocas, troncos y hojas
        bloques = new Bloque[(anchoPantalla / altoBloque) * 6 + (troncosPorPila + 4) * numPilas];

        // Crear bloques de césped
        for (int i = 0; i < numBloques; i++) {
            bloques[i] = new Cesped(i * altoBloque, 300, altoBloque); // Ajustado para la nueva altura del suelo
        }

        // Crear filas de rocas debajo del césped
        for (int i = 0; i < numBloques; i++) {
            bloques[numBloques + i] = new Tierra(i * altoBloque, 320, altoBloque);
        }
        for (int i = 0; i < numBloques; i++) {
            bloques[numBloques * 2 + i] = new Roca(i * altoBloque, 340, altoBloque);
        }
        for (int i = 0; i < numBloques; i++) {
            bloques[numBloques * 3 + i] = new Roca(i * altoBloque, 360, altoBloque);
        }
    }

    private void crearTroncosYHojas() {
        int currentIndex = (anchoPantalla / altoBloque) * 2;

        for (int i = 0; i < numPilas; i++) {
            int offset = (i == 0) ? 20 : 0;
            int x = i * altoBloque * 5 + offset;

            for (int j = 0; j < troncosPorPila; j++) {
                bloques[currentIndex++] = new Tronco(x, 300 - (j + 1) * altoBloque, altoBloque);
            }

            bloques[currentIndex++] = new Hojas(x, 300 - (troncosPorPila + 1) * altoBloque, altoBloque);
            bloques[currentIndex++] = new Hojas(x, 300 - (troncosPorPila + 2) * altoBloque, altoBloque);

            bloques[currentIndex++] = new Hojas(x - altoBloque, 300 - (troncosPorPila + 1) * altoBloque, altoBloque);
            bloques[currentIndex++] = new Hojas(x + altoBloque, 300 - (troncosPorPila + 1) * altoBloque, altoBloque);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        
        // Dibujar todos los bloques
        for (Bloque bloque : bloques) {
            if (bloque != null) { // Asegurarse de no dibujar bloques eliminados
                bloque.dibujar(g);
            }
        }
        if (personaje.getDireccion() == 'd' && imagenDerecha != null) {
            g.drawImage(imagenDerecha, personaje.getX(), personaje.getY(), 40, 40, this);
        } else if (personaje.getDireccion() == 'a' && imagenIzquierda != null) {
            g.drawImage(imagenIzquierda, personaje.getX(), personaje.getY(), 40, 40, this);
        } else {
            g.fillRect(personaje.getX(), personaje.getY(), 40, 50);
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
            for (int i = 0; i < bloques.length; i++) {
                if (bloques[i] == bloqueSeleccionado) {
                    index = i;
                    break;
                }
            }
        }
        if (key == KeyEvent.VK_LEFT) {
            if (index > 0) {
                bloqueSeleccionado = bloques[index - 1];
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if (index < bloques.length - 1) {
                bloqueSeleccionado = bloques[index + 1];
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
      
        for (int i = 0; i < bloques.length; i++) {
            if (bloques[i] == bloque) {
                // Desplaza los elementos hacia la izquierda
                for (int j = i; j < bloques.length - 1; j++) {
                    bloques[j] = bloques[j + 1]; // Mueve el siguiente elemento a la posición actual
                }
                bloques[bloques.length - 1] = null; // Opcional: Limpia el último elemento
                break; // Salimos del bucle después de eliminar el bloque
            }
        }
    
        
        repaint(); // Actualiza el lienzo después de eliminar el bloque
    }
}
