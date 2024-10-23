package terraria;

public class Personaje {
    private int salud;
    private int hambre;
    private int x;
    private int y;
    private int experiencia;  // Nuevo atributo
    private char direccion;  // Nuevo atributo para la dirección

    // Constructor por defecto
    public Personaje() {
        this.salud = 100;
        this.hambre = 100;
        this.x = 0;
        this.y = 0;
        this.experiencia = 0;  // Inicialización de experiencia
        this.direccion = 'd';  // Inicializa la dirección a la derecha por defecto
    }

    // Constructor con parámetros
    public Personaje(int salud, int hambre, int x, int y, int experiencia) {
        this.salud = salud;
        this.hambre = hambre;
        this.x = x;
        this.y = y;
        this.experiencia = experiencia;  // Inicialización de experiencia
        this.direccion = 'd';  // Inicializa la dirección a la derecha por defecto
    }

    // Constructor copia
    public Personaje(Personaje otroPersonaje) {
        this.salud = otroPersonaje.salud;
        this.hambre = otroPersonaje.hambre;
        this.x = otroPersonaje.x;
        this.y = otroPersonaje.y;
        this.experiencia = otroPersonaje.experiencia;  // Copiar experiencia
        this.direccion = otroPersonaje.direccion;  // Copiar dirección
    }

    // Métodos getter y setter para dirección
    public char getDireccion() {
        return direccion;
    }

    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }

    // Método para mover al personaje
    public void mover(char movimiento) {
        if (movimiento == 'd') {
            x = x + 50;
        } else if (movimiento == 'a') {
            x = x - 50;
        }
        this.direccion = movimiento;  // Actualiza la dirección
    }

    // Método para reducir la salud
    public void reducirSalud(int cantidad) {
        this.salud -= cantidad;
        if (this.salud < 0) {
            this.salud = 0;
        }
    }

    // Método para reducir el hambre
    public void reducirHambre(int cantidad) {
        this.hambre -= cantidad;
        if (this.hambre < 0) {
            this.hambre = 0;
        }
    }

    // Método para aumentar la experiencia
    public void aumentarExperiencia(int cantidad) {
        this.experiencia += cantidad;
    }

    // Getters y setters para los atributos
    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
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

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    // Método para imprimir la información del personaje
    public void mostrarInformacion() {
        System.out.println("Salud: " + this.salud);
        System.out.println("Hambre: " + this.hambre);
        System.out.println("Posición: (" + this.x + ", " + this.y + ")");
        System.out.println("Experiencia: " + this.experiencia);  // Mostrar experiencia
    }
}
