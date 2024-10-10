package terraria;

import java.util.Scanner;

public class Terraria {

    public static void main(String[] args) {



        Personaje personaje1 = new Personaje(4, 4, 0, 0);
        
        System.out.println("holaosición inicial X: " + personaje1.getX());
       
        Scanner scanner = new Scanner(System.in);
        char movimiento;
        boolean salida=true;
        while (salida==true) {

            movimiento = scanner.next().charAt(0); // Leer el primer carácter ingresado

            if (movimiento == 'Q' || movimiento == 'q') {
                // Salir del bucle si el jugador ingresa 'Q' o 'q'
                System.out.println("¡Has salido del juego!");
                salida=false;
            }
            if(salida==true){
                personaje1.mover(movimiento);
                System.out.println(personaje1.getX());

            }
           
            
        }
    }
}
