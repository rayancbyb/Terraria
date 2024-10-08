package terraria;

import java.util.Scanner;

public class Terraria {

    public static void main(String[] args) {

        Personaje personaje1 = new Personaje(4, 4, 0, 0);

        System.out.println("Posición inicial X: " + personaje1.getX() + ", Y: " + personaje1.getY());

        Scanner scanner = new Scanner(System.in);
        char movimiento;
        boolean salida = true;

        while (salida) {
            movimiento = scanner.next().charAt(0); // Leer el primer carácter ingresado

            if (movimiento == 'Q' || movimiento == 'q') {
                // Salir del bucle si el jugador ingresa 'Q' o 'q'
                System.out.println("¡Has salido del juego!");
                salida = false;
            } else {
                if (movimiento == 'w') {
                    personaje1.saltar(movimiento);  // Mover hacia arriba (saltar)
                } else {
                    personaje1.mover(movimiento);  // Mover izquierda/derecha
                }
                System.out.println("Posición actual X: " + personaje1.getX() + ", Y: " + personaje1.getY());
            }
        }
    }
}
