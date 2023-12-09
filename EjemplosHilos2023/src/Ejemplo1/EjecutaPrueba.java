package Ejemplo1;

/**
 *
 * @author leovi
 */
public class EjecutaPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HiloPrueba h1 = new HiloPrueba("hilo 1", (int)(Math.random()*2000));
        HiloPrueba h2 = new HiloPrueba("hilo 2", (int)(Math.random()*2000));
        HiloPrueba h3 = new HiloPrueba("hilo 3", (int)(Math.random()*2000));
        
        h1.start();
        h2.start();
        h3.start();
        System.out.println("se terminó el main");
    }
    
}
