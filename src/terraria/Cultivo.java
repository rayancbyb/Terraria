package terraria;

import java.util.concurrent.TimeUnit;

public enum Cultivo {
    TRIGO(7, 10),
    MAIZ(10, 15),
    ZANAHORIA(5, 8);

    private int tiempoCultivo;
    private int experiencia;

    // Constructor con parámetros
    Cultivo(int tiempoCultivo, int experiencia) {
        this.tiempoCultivo = tiempoCultivo;
        this.experiencia = experiencia;
    }

    // Constructor por defecto (asignando valores por defecto)
    Cultivo() {
        this.tiempoCultivo = 0; // Tiempo por defecto
        this.experiencia = 0;    // Experiencia por defecto
    }

    // Métodos getter
    public int getTiempoCultivo() {
        return tiempoCultivo;
    }

    public int getExperiencia() {
        return experiencia;
    }

    // Método para simular el crecimiento
  public void crecer(Personaje personaje) {
        System.out.println("El " + this.name().toLowerCase() + " está creciendo...");
        try {
            TimeUnit.SECONDS.sleep(this.tiempoCultivo);
        } catch (InterruptedException e) {
            System.out.println("Error en la simulación del crecimiento.");
        }
        System.out.println("El " + this.name().toLowerCase() + " ha terminado de crecer.");
        personaje.aumentarExperiencia(this.experiencia);
        System.out.println("El personaje ha ganado " + this.experiencia + " puntos de experiencia.");
    }

    // Método para mostrar los valores del cultivo
    public String mostrarInfo() {
        return "Cultivo: " + this.name() + " | Tiempo de cultivo: " + tiempoCultivo + " segundos | Experiencia: " + experiencia;
    }
}
