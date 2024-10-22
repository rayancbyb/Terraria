package terraria;

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

        // Mostrar información inicial
        personaje1.mostrarInformacion();
        Cultivo zanahoria = Cultivo.ZANAHORIA;
        System.out.println(zanahoria.mostrarInfo());
        zanahoria.crecer(personaje1);
        personaje1.mostrarInformacion();
    }
}
