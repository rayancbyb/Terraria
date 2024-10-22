package terraria;

import java.awt.Graphics;
import java.awt.Image;

public class Bloque {
    private int x;
    private int y;
    private int tamaño;
    private String tipo;
    private Image imagen;

    public Bloque(int x, int y, int tamaño, String tipo, Image imagen) {
        this.x = x;
        this.y = y;
        this.tamaño = tamaño;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, tamaño, tamaño, null);
        } else {
            g.drawRect(x, y, tamaño, tamaño);
            g.drawString(tipo, x, y + tamaño + 10);
        }
    }
}