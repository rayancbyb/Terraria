package terraria;

public class Personaje {
    private int salud;
    private int hambre;
    private int x;
    private int y;

    // Constructor por defecto
    public Personaje() {
        this.salud = 100;
        this.hambre = 100;
        this.x = 0;
        this.y = 0;
    }

    // Constructor con parámetros
    public Personaje(int salud, int hambre, int x, int y) {
        this.salud = salud;
        this.hambre = hambre;
        this.x = x;
        this.y = y;
    }

    // Método para mover al personaje
    public void mover(char movimiento) {
      
        if(movimiento =='d'){
            x=x+1;
       }else if(movimiento == 'a'){
            x=x-1;
       }
    
    }

  // Método para saltar al personaje
  public void saltar(char movimiento) {
      
    if(movimiento =='w'){
        y=y+1;
        
   }

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

    // Método para imprimir la información del personaje
    public void mostrarInformacion() {
        System.out.println("Salud: " + this.salud);
        System.out.println("Hambre: " + this.hambre);
        System.out.println("Posición: (" + this.x + ", " + this.y + ")");
    }
}
