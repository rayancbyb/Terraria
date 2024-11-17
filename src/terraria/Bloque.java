package terraria;

import java.awt.Graphics;
import java.awt.Image;

public class Bloque {
    private int x;
    private int y;
    private int tamaño;
    private String tipo;
    private Image imagen;
    private int resistencia; // Tiempo en segundos para romper el bloque
    private int durabilidadActual; // La resistencia restante del bloque

    public Bloque(int x, int y, int tamaño, String tipo, Image imagen, int resistencia) {
        this.x = x;
        this.y = y;
        this.tamaño = tamaño;
        this.tipo = tipo;
        this.imagen = imagen;
        this.resistencia = resistencia;
        this.durabilidadActual = resistencia; // Al inicio, la durabilidad es igual a la resistencia.
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getTamaño() { return tamaño; }
    public void setTamaño(int tamaño) { this.tamaño = tamaño; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getResistencia() { return resistencia; }
    public void setResistencia(int resistencia) { this.resistencia = resistencia; }
    public int getDurabilidadActual() { return durabilidadActual; }
    public void setDurabilidadActual(int durabilidadActual) { this.durabilidadActual = durabilidadActual; }

    // Método para dibujar el bloque
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, tamaño, tamaño, null);
        } else {
            g.drawRect(x, y, tamaño, tamaño);
            g.drawString(tipo, x, y + tamaño + 10);
        }
    }

    // Método para "romper" el bloque (reduce la durabilidad)
    public void romper(int tiempoPasado) {
        durabilidadActual -= tiempoPasado;
        if (durabilidadActual < 0) {
            durabilidadActual = 0; // Asegurarse de que no sea negativo
        }
    }

    // Método para verificar si el bloque ha sido destruido
    public boolean estaDestruido() {
        return durabilidadActual <= 0;
    }
}
