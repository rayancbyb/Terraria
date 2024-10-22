package terraria;

import java.util.Scanner;
import javax.swing.JFrame;

public class Terraria {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Terraria");
        ventana.setSize(900, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Personaje personaje1 = new Personaje(100, 100, 50, 530, 0);

        Lienzo lienzo = new Lienzo(personaje1);
        ventana.add(lienzo);

        ventana.setVisible(true);

        Scanner scanner = new Scanner(System.in);
        char movimiento;
        boolean enJuego = true;

        personaje1.mostrarInformacion();  // Muestra la info actualizada

        Cultivo zanahoria = Cultivo.ZANAHORIA;
        System.out.println(zanahoria.mostrarInfo());
        zanahoria.crecer(personaje1);

        personaje1.mostrarInformacion();  // Muestra la info actualizada

        while (enJuego) {
            System.out.println("Introduce un movimiento (A/D) o Q para salir: ");
            movimiento = scanner.next().charAt(0);
            if (movimiento == 'Q' || movimiento == 'q') {
                System.out.println("¡Has salido del juego!");
                enJuego = false;
            } else {
                personaje1.mover(movimiento); // Mueve el personaje
                personaje1.reducirHambre(1);  // Simula que el personaje tiene hambre al moverse
                personaje1.mostrarInformacion();  // Muestra la info actualizada
                lienzo.repaint();  // Redibuja el lienzo para mostrar el personaje en su nueva posición
            }
        }
        scanner.close();
    }
}
