package terraria;

public class Cubo {
    // Atributos
    private int a;
    private int b;
    
    // Constructor para inicializar los valores de 'a' y 'b'
    public Cubo(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    // Método que retorna la suma de 'a' y 'b'
    public int sumar() {
        return this.a + this.b;
    }
}
