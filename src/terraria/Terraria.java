package terraria;

import java.util.Scanner;

public class Terraria {

    public static void main(String[] args) {
        // Crear un personaje inicial
        Personaje personaje1 = new Personaje(100, 100, 0, 0, 0);
        
        Scanner scanner = new Scanner(System.in);
        char movimiento;
        boolean enJuego = true;
        
        
                                personaje1.mostrarInformacion();  // Muestra la info actualizada

        
       
        Cultivo zanahoria = Cultivo.ZANAHORIA;

        // Mostrar información de los cultivos
    
        System.out.println(zanahoria.mostrarInfo());

        // Simular el crecimiento de los cultivos
       
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
            }
        }

        scanner.close();
    }
}

